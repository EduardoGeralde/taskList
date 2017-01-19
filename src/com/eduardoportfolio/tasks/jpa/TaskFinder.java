package com.eduardoportfolio.tasks.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.eduardoportfolio.tasks.model.Task;

public class TaskFinder {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Task");
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createQuery("select t from Task as t where t.isComplete = "
																	+ ":paramFinalized");
		query.setParameter("paramFinalized", true);
		
		List<Task> list = query.getResultList();
		
		for (Task task : list){
			System.out.println(task.getDescription());
		}
		
		manager.close();
	}
}
