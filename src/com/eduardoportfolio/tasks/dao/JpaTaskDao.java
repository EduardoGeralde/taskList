package com.eduardoportfolio.tasks.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.eduardoportfolio.tasks.model.Task;

@Repository
public class JpaTaskDao implements TaskDao {
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public Task selectById(Long id) {
		return manager.find(Task.class, id);
	}

	@Override
	public List<Task> list() {
		return manager.createQuery("select t from Task t").getResultList();
	}

	@Override
	public void add(Task task) {
		manager.persist(task);
	}

	@Override
	public void update(Task task) {
		manager.merge(task);
	}

	@Override
	public void remove(Task task) {
		Task taskToRemove = selectById(task.getId());
		manager.remove(taskToRemove);
	}

	@Override
	public void endTask(Long id) {
		Task task = selectById(id);
		task.setIsComplete(true);
		task.setFinalizedDate(Calendar.getInstance());
	}

}
