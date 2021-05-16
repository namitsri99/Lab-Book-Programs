package com.cg.jpa.ui;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cg.jpa.entity.Author;
import com.cg.jpa.entity.Book;
import com.cg.jpa.util.JPAUtil;

public class MethodsImpl {

	static final Scanner scan = new Scanner(System.in);
	static final EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	static final EntityManager em = factory.createEntityManager();
	static final EntityTransaction txn = em.getTransaction();

	public static void main(String[] args) {
		MethodMenu[] menus = MethodMenu.values();
		MethodMenu selectedMenu = null;

		while (selectedMenu != MethodMenu.Quit) {
			System.out.println("Book with author");
			for (MethodMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case FindAll:
					doFindAllBooks();
					break;
				case FindBookByAuthorId:
					doFindBookByAuthorId();
					break;
				case FindBookInRange:
					doFindBookInRange();
					break;
				case FindAuthorByBookId:
					doFindAuthorByBookId();
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

	private static void doFindAuthorByBookId() {
		System.out.println("Enter Book Id");
		int bookid = scan.nextInt();
		Book b1 = em.find(Book.class, bookid);
		if (b1 == null) {
			System.out.println("No book with this id");
		} else {
			Author author = b1.getAuthor();
			System.out.println(author);
		}
	}

	private static void doFindBookInRange() {
		System.out.println("Enter Min Price");
		int minPrice = scan.nextInt();
		System.out.println("Enter Max Price");
		int maxPrice = scan.nextInt();

		Query q = em.createQuery("select e from Book e where price between :a and :b");
		q.setParameter("a", minPrice);
		q.setParameter("b", maxPrice);

		List<Book> b1 = q.getResultList();
		if (b1 != null) {
			for (Book book : b1) {
				System.out.println(book);
			}
		} else {
			System.out.print("No books in this range");
		}
	}

	private static void doFindBookByAuthorId() {
		System.out.println("Enter Author Id");
		int authorId = scan.nextInt();
		Author author = em.find(Author.class, authorId);
		if (author == null) {
			System.out.println("No books written by the author");
		} 
		else {
			List<Book> b1 = author.getBook();
			if (b1.isEmpty()) {
				System.out.println("No books written by the author");
			}
			else {
				for (Book book : b1) {
					System.out.println(book);
				}

			}
		}
	}

	private static void doFindAllBooks() {
    	String jpql = "SELECT e FROM Book e";
		TypedQuery<Book> tqry = em.createQuery(jpql, Book.class);
		List<Book> b1 = tqry.getResultList();
		for (Book book : b1) {
			System.out.println(book);
		}

	}

}
