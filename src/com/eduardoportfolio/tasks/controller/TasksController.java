package com.eduardoportfolio.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.tasks.dao.JdbcTaskDao;
import com.eduardoportfolio.tasks.model.Task;

@Controller
public class TasksController {

	@RequestMapping("newTask")
	public String form() {
		return "task/taskForm";
	}
	
	@RequestMapping ("addTask")
	public String add(Task task) {
		
		JdbcTaskDao dao = new JdbcTaskDao ();
		dao.create(task);
		
		return"task/added";
	}
}
