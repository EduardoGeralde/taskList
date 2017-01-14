package com.eduardoportfolio.tasks.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String add(@Valid Task task, BindingResult result) {
		
		if(result.hasFieldErrors("description")){
			return "task/taskForm";
		}
		
		JdbcTaskDao dao = new JdbcTaskDao ();
		dao.create(task);
		
		return"task/added";
	}
	
	@RequestMapping ("taskList")
	public String list(Model model){
		
		JdbcTaskDao dao = new JdbcTaskDao();
		model.addAttribute("tasks", dao.getList());
		
		return "task/taskList";
	}
}
