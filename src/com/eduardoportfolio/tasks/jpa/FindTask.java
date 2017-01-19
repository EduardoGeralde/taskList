package com.eduardoportfolio.tasks.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.eduardoportfolio.tasks.model.Task;

public class FindTask {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Task");
		EntityManager manager = factory.createEntityManager();
		
		Task found = manager.find(Task.class,3L);
		
		System.out.println(found.getDescription());
		
		manager.close();
	}
}
