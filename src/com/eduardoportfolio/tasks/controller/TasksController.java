package com.eduardoportfolio.tasks.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		return"redirect:taskList";
	}
	
	@RequestMapping ("taskList")
	public String list(Model model){
		
		JdbcTaskDao dao = new JdbcTaskDao();
		model.addAttribute("tasks", dao.getList());
		
		return "task/taskList";
	}
	
	@ResponseBody
	@RequestMapping("removeTask")
	public void remove(Task task){
		JdbcTaskDao dao = new JdbcTaskDao();
		dao.remove(task);
	}
	
	@RequestMapping("showTask")
	public String show(Long id, Model model){
		
		JdbcTaskDao dao = new JdbcTaskDao();
		model.addAttribute("task", dao.selectById(id));
		
		return "task/show";	
	}
	
	@RequestMapping("updateTask")
	public String update(Task task){
		
		JdbcTaskDao dao = new JdbcTaskDao();
		dao.upDate(task);
		//"redirect" is used on the client side, for a server-side, "forward" is used
		return "redirect:taskList";
	}
	
	@RequestMapping ("finalizeTask")
	public String finalize(Long id, Model model){
		
		JdbcTaskDao dao = new JdbcTaskDao();
		dao.endTask(id);
		
		model.addAttribute("task", dao.selectById(id));
		
		return "task/finalized";
	}
	
	
}
