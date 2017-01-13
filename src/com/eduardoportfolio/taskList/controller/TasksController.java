package com.eduardoportfolio.taskList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.taskList.dao.JdbcTaskDao;
import com.eduardoportfolio.taskList.model.Task;

@Controller
public class TasksController {

	@RequestMapping("newTask")
	public String form() {
		return "task/taskForm";
	}
	
	@RequestMapping ("addTask")
	public String add(Task taskList) {
		
		JdbcTaskDao dao = new JdbcTaskDao ();
		dao.create(taskList);
		
		return"task/added";
	}
}
