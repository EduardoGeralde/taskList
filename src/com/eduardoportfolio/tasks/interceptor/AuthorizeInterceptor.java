package com.eduardoportfolio.tasks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Eduardo Geralde Neto
 * 
 * This intercepter class is used to check if the user is already logged and authorized to proceed in the
 * application. If it is not already logged, then this intercepter redirects to the login form.
 * It ignores if the user is in login form, login action or with any file of resources directory
 */

public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("loginForm") || uri.endsWith("makeLogin") || uri.contains("resources")){
			return true;
		}
		
		if (request.getSession().getAttribute("userLogged") != null){
			return true;
		}
		
		response.sendRedirect("loginForm");
		return false;
	}
}
