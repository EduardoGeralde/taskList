package com.eduardoportfolio.tasks.jpa;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.eduardoportfolio.tasks.model.Task;

public class AddTask {

	public static void main(String[] args) {
		
		Task task = new Task();
		task.setDescription("Study R Language");
		task.setIsComplete(true);
		task.setFinalizedDay(Calendar.getInstance());
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Task");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(task);
		manager.getTransaction().commit();
		
		System.out.println("Task ID: " + task.getId());
		
		manager.close();
	}
}
