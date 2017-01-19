package com.eduardoportfolio.tasks.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenerateTables {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Task");
		factory.close();
	}
}
