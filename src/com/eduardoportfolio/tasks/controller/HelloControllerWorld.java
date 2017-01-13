package com.eduardoportfolio.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloControllerWorld {
	
	@RequestMapping("/helloSpringWorld")
	public String execute (){
		
		System.out.println("Executing logic with Spring MVC");
		
		return "ok";
	}
}
