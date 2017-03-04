package com.eduardoportfolio.tasks.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.tasks.model.Task;

/**
 * @author Eduardo Geralde Neto
 * 
 * It has our CRUD and other methods that deal with the behavior of our tasks using JPA specification.
 * The only responsible to access, change, add and remove our data.
 */

//Declaring this class as a component
@Repository
public class JpaTaskDao implements TaskDao {
	
	//Spring Inject EntityManager with JPA annotation
	@PersistenceContext
	EntityManager manager;

	//Select a specific task with a given id
	@Override
	public Task selectById(Long id) {
		return manager.find(Task.class, id);
	}

	//List all tasks that are in the DB
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> list() {
		return manager.createQuery("select t from Task t").getResultList();
	}

	//Create a new task on DB
	@Override
	public void add(Task task) {
		manager.persist(task);
	}

	//Update the task on DB with given task data
	@Override
	public void update(Task task) {
		manager.merge(task);
	}

	//Remove the task on DB with a given id
	@Override
	public void remove(Task task) {
		Task taskToRemove = selectById(task.getId());
		manager.remove(taskToRemove);
	}

	//Turn the task completed, and sets the date with the current time with a given id
	@Override
	public void endTask(Long id) {
		Task task = selectById(id);
		task.setIsComplete(true);
		task.setFinalizedDate(Calendar.getInstance());
	}

}
