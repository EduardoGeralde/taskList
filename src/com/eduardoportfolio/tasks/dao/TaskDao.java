package com.eduardoportfolio.tasks.dao;

import java.util.List;

import com.eduardoportfolio.tasks.model.Task;

public interface TaskDao {
	
	Task selectById(Long id);
	List<Task> list();
	void add(Task task);
	void update(Task task);
	void remove(Task task);
	void endTask(Long id);
}
