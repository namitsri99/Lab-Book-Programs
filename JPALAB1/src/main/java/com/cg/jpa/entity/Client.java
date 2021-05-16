package com.cg.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.cg.jpa.util.JPAUtil;


public class Client {

	public static void main(String[] args) {
		EntityManagerFactory factory =  JPAUtil.getEntityManagerFactory();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Greet greet = new Greet();
		greet.setMessage("Welcome to JPA!");
		
		em.persist(greet);
		
		System.out.println("Added greeting to database.");

		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}


