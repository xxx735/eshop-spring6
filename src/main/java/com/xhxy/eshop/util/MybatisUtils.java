package com.xhxy.eshop.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	public static SqlSession getSqlSession() {
		try {
			// 1. 读取配置文件
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			// 2. 创建SqlSessionFactory
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 3. 打开SqlSession
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	// true参数表示会自动提交
			
			return sqlSession;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
