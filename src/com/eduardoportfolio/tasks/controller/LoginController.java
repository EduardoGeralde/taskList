package com.eduardoportfolio.tasks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.tasks.dao.JdbcUserDao;
import com.eduardoportfolio.tasks.model.User;

@Controller
public class LoginController {

	@RequestMapping ("loginForm")
	public String loginForm(){
		return "login-form";
	}
	
	@RequestMapping("makeLogin")
	public String makeLogin(User user, HttpSession session){
		
		if(new JdbcUserDao().userExists(user)) {
			session.setAttribute("userLogged", user);
			return "menu";
		}
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
