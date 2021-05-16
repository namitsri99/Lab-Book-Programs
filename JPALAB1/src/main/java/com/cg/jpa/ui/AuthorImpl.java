package com.cg.jpa.ui;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.cg.jpa.entity.Author;
import com.cg.jpa.util.JPAUtil;

public class AuthorImpl {

	static final Scanner scan = new Scanner(System.in);
	static final EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	static final EntityManager em = factory.createEntityManager();
	static final EntityTransaction txn = em.getTransaction();

	public static void main(String[] args) {
		AuthorMenu[] menus = AuthorMenu.values();
		AuthorMenu selectedMenu = null;

		while (selectedMenu != AuthorMenu.Quit) {
			System.out.println("Crud operation on author class");
			for (AuthorMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case  InsertAuthor:
					doInsert();
					break;
				case UpdateAuthor:
					doUpdate();
					break;
				case DeleteAuthor:
					doDelete();
					break;
				case Quit:
					break;
				}
			} else {
				selectedMenu = null;
			}

		}
		scan.close();
		System.out.println("Invalid choice");

	}

	private static void doInsert() {
			System.out.print("Enter First Name :");
			String firstName = scan.next();
			System.out.print("Enter Middle Name :");
			String middleName = scan.next();
			System.out.print("Enter Last Name :");
			String lastName = scan.next();
			System.out.print("Enter Phone Number :");
			String number = scan.next();			
			Author author = new Author(firstName,middleName,lastName,number);
			EntityTransaction txn = em.getTransaction();
			txn.begin();
			em.persist(author);
			txn.commit();
	}

	private static void doUpdate() {
		System.out.print("Enter Author Id whose details are to be updated :");
		int authorId = scan.nextInt();
		try {
			Author author = em.find(Author.class, authorId);
			if (author == null) {
				throw new Exception("No author details updation with #" + authorId + " is possible as it doesn't exist.");
			} else {
				System.out.print("Enter Updated First Name :");
				String firstName = scan.next();
				author.setFirstName(firstName);
				System.out.print("Enter Updated Middle Name :");
				String middleName = scan.next();
				author.setMiddleName(middleName);
				System.out.print("Enter Updated Last Name :");
				String lastName = scan.next();
				author.setLastName(lastName);
				System.out.print("Enter Updated Phone Number :");
				String number = scan.next();
				author.setPhoneNo(number);	
				txn.begin();
				em.persist(author);
				txn.commit();
				System.out.println("Author updated");
			}
		} catch (Exception excep) {
			System.out.println("author id not update");
		}
	}


	private static void doDelete() {
		try {
			System.out.println("Enter Author Id :");
			int authorId = scan.nextInt();
			Author author = em.find(Author.class, authorId);
			if (author == null) {
				throw new Exception("No author with id #" + authorId);
			} else {
				txn.begin();
				em.remove(author);
		      	txn.commit();
				System.out.println("Author removed");
			}
		} catch (Exception excep) {
			System.out.println("Author is not  find");
		}
	}
}
