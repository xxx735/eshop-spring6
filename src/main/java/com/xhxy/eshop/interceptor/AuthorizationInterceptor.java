package com.xhxy.eshop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取会话session中的userId属性
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // 若有userId属性，说明已登录；否则，未登录，转向登录网页
        if (userId == null) { // 用户尚未登录
            request.getRequestDispatcher("/user/login").forward(request, response);
            return false;
        } else { // 用户已登录，则验证通过
            return true;
        }

    }
}
