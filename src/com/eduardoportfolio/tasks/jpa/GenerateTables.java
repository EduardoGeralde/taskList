package com.eduardoportfolio.tasks.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eduardo Geralde Neto
 * 
 * This is a example of how generate the table manually with JPA.
 */

public class GenerateTables {

	public static void main(String[] args) {
		
		//The Persistence class (same name of the XML) is responsible to load the XML and initialize
		//the configurations. The result of this configurations is a EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Task");
		factory.close();
	}
}
