package com.xhxy.eshop.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

/**
 * 字符编码过滤器
 */
@WebFilter(filterName = "characterFilter", description = "字符编码过滤器，解决中文乱码", urlPatterns = { "/*" })
public class CharacterFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public CharacterFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 设置request和response的字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 继续沿 过滤链 往下传
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
