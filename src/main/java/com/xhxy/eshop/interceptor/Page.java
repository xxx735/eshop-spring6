package com.xhxy.eshop.interceptor;

public class Page
{
	private Integer totalPage;		// 总页数
	private Integer pageSize = 3;	// 每页的条数
	private Integer pageIndex = 1;	// 当前页号

	// 初始化全部成员变量的构造器
	public Page(Integer pageSize, Integer pageIndex)
	{
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	// totalPage的setter和getter方法
	public void setTotalPage(Integer totalPage)
	{
		this.totalPage = totalPage;
	}
	public Integer getTotalPage()
	{
		return this.totalPage;
	}

	// pageSize的getter方法
	public Integer getPageSize()
	{
		return this.pageSize;
	}

	// pageIndex的getter方法
	public Integer getPageIndex()
	{
		return this.pageIndex;
	}
}