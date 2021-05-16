package com.cg.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;
	private String name;
	
	@OneToMany(mappedBy = "author")
	private List<Book> book = new ArrayList<>();

	public Author() {
	}

	public Author(String name) {
		super();
		this.name = name;
	}

	public int getAuthorId() {
		return authorId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return String.format("Author [authorId=%s, name=%s]", authorId, name);
	}
	
}