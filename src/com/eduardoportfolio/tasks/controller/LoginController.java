package com.eduardoportfolio.tasks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.tasks.dao.JdbcUserDao;
import com.eduardoportfolio.tasks.model.User;

/**
 * @author Eduardo Geralde Neto
 * 
 * This class controls the flows of the logged users into the sessions, when user is logged, the controller send
 * to menu page, but, if it is not, the controller returns to login form.
 */

@Controller
public class LoginController {

	//Return to the login-form.jsp
	@RequestMapping ("loginForm")
	public String loginForm(){
		return "login-form";
	}
	
	//Registering a logged user in the session
	@RequestMapping("makeLogin")
	public String makeLogin(User user, HttpSession session){
		
		if(new JdbcUserDao().userExists(user)) {
			session.setAttribute("userLogged", user);
			return "menu";
		}
		return "redirect:loginForm";
	}
	
	//LogOut the user from the session
	@RequestMapping("logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
