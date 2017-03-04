package com.eduardoportfolio.tasks.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eduardoportfolio.tasks.dao.TaskDao;
import com.eduardoportfolio.tasks.model.Task;

/**
 * @author Eduardo Geralde Neto
 * 
 * This class controls the flow of our application.  Which one of theses methods represents actions (business 
 * rules), and after apply the correct action, it returns to the correspondent view or redirect to another
 * action. The responsibility of ours actions and views, comes from here.
 */

//Enables Spring transaction management for any method of this class
@Transactional
//Is part of Spring MVC and it controls the object
@Controller
public class TasksController {
	
	//With the mysqlDataSource defined in the XML,  Spring injects the dependency
	@Autowired
	//Using JpaTaskDao to inject
	@Qualifier("jpaTaskDao")
	TaskDao dao;
	
	//Return to the task form
	@RequestMapping("newTask")
	public String form() {
		return "task/taskForm";
	}
	
	//Add a new task in our BD and move the flow to other action. Some validation are applied through 
	//annotations in the attribute model, then, if it has any error, the user is redirect to the task form 
	//again
	@RequestMapping ("addTask")
	public String add(@Valid Task task, BindingResult result) {
		
		if(result.hasFieldErrors("description")){
			return "task/taskForm";
		}
		
		dao.add(task);
		
		return"redirect:taskList";
	}
	
	//List all tasks and stores this in a object Model, returning it with the String to the JSP
	@RequestMapping ("taskList")
	public String list(Model model){
		
		model.addAttribute("tasks", dao.list());
		
		return "task/taskList";
	}
	
	//Remove the task and returns code 200 to callback function of AJAX
	@ResponseBody
	@RequestMapping("removeTask")
	public void remove(Task task){
		
		dao.remove(task);
	}
	
	//Select a specific task by a given id, add it in a object Model and returns it to a JSP. In this case, it
	//will be use to update this task data
	@RequestMapping("showTask")
	public String show(Long id, Model model){
		
		model.addAttribute("task", dao.selectById(id));
		
		return "task/show";	
	}
	
	//Update the task and redirect to the taskList action
	@RequestMapping("updateTask")
	public String update(Task task){
		
		dao.update(task);
		
		return "redirect:taskList";
	}
	
	//Finalize the task through the endTask method, stores this task in a object Model and returns it to
	//the correct JSP
	@RequestMapping ("finalizeTask")
	public String finalize(Long id, Model model){
		
		dao.endTask(id);
		
		model.addAttribute("task", dao.selectById(id));
		
		return "task/finalized";
	}
	
	
}
