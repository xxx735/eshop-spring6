package com.xhxy.eshop.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.xhxy.eshop.interceptor.Page;

@Intercepts({
	// 指定该插件拦截StatementHandler的prepare(Connection, Integer)方法
	@Signature(type = StatementHandler.class, method = "prepare",
		args = {Connection.class, Integer.class }) })
public class PagePlugin implements Interceptor
{
	@Override
	@SuppressWarnings("unchecked")
	public Object intercept(Invocation invocation) throws Throwable
	{
		// 获取被拦截的目标对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 获取statementHandler对应的MetaObject对象
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);  // ①
		// 通过MetaObject获取本次执行的MappedStatement对象
		// MappedStatement代表XML Mapping中的select, insert、update, delete元素
		MappedStatement mappedStatement = (MappedStatement) metaObject
			.getValue("delegate.mappedStatement");  // ②
		// 获取执行的MappedStatement的id属性值（对应于Mapper组件的方法名）
		String id = mappedStatement.getId();
		// 如果方法名以Page结尾，说明是需要分页的方法
		if (id.endsWith("Page"))
		{
			BoundSql boundSql = statementHandler.getBoundSql();
			// 获取传给Mapper方法的参数
			Map<String,Object> paramMap = (Map<String, Object>) boundSql
				.getParameterObject();
			// 定义page变量用于保存分页参数
			Page page = null;
			// 先尝试获取名为page的命名参数（以@Param("page")修饰的参数）
			try
			{
				page = (Page) paramMap.get("page");
			}
			// 如果没找到名为page的命名参数
			catch (BindingException ex)
			{
				// 遍历paramMap（paramMap代表传给Mapper方法的实际参数）
				for (String key : paramMap.keySet())
				{
					Object val = paramMap.get(key);
					// 如果该参数的类型是Page，说明找到了page参数
					if (val.getClass() == Page.class)
					{
						page = (Page) val;
					}
				}
			}
			// 如果page依然为null，说明没法找到分页参数
			if (page == null)
			{
				throw new IllegalArgumentException("Page Parameter can't be null.");
			}
			// 获取Mapper组件实际要执行的SQL
			String sql = boundSql.getSql();
			// 生成一条统计总记录数的SQL语句
			String countSql = "select count(*) from (" + sql + ") a";
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement = connection
				.prepareStatement(countSql);
			// 获取ParameterHandler对象
			ParameterHandler parameterHandler = statementHandler.getParameterHandler();
//			// 也可通过如下代码利用MetaObject获取
//			var parameterHandler = (ParameterHandler) metaObject
//				.getValue("delegate.parameterHandler");   // ③
			// 为PreparedStatement中的SQL传入参数
			parameterHandler.setParameters(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
			{
				int totalRec = rs.getInt(1);
				// 计算总页数
				page.setTotalPage(totalRec / page.getPageSize() == 0 ?
					totalRec / page.getPageSize() : totalRec / page.getPageSize() + 1);
			}
			// 修改SQL语句，增加分页语法（只针对MySQL）
			String pageSql = sql + " limit "
				+ (page.getPageIndex() - 1) * page.getPageSize() + ", "
				+ page.getPageSize();
			// 改变Mapper方法实际要执行的SQL语句
			metaObject.setValue("delegate.boundSql.sql", pageSql);   // ④
		}
		return invocation.proceed();
	}
	@Override
	public Object plugin(Object o)
	{
		return Plugin.wrap(o, this);
	}
	@Override
	public void setProperties(Properties properties)
	{
	}
}

