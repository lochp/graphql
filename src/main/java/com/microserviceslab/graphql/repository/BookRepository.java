package com.microserviceslab.graphql.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.microserviceslab.graphql.model.Book;
import com.microserviceslab.graphql.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("bookRepository")
public class BookRepository {

	@Autowired
	private DatabaseClient databaseClient;
	
	public Mono<Book> getBook(int id) {
		return databaseClient.sql(String.format("SELECT * FROM books WHERE id = %d", id)).map(row -> new Book(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class))).one();
	}
	
	public Mono<Book> getBookByName(final String name) {
		return databaseClient.sql(String.format("SELECT * FROM books WHERE name = %s", name))
				.map(row -> new Book(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class))).one();
	}
	
	public Flux<Book> getBooks() {
		return databaseClient.sql("SELECT * FROM books").map(row -> new Book(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class))).all();
	}
	
	public Mono<Integer> createBook(Book book) {
		Integer id = Utils.genId();
		return databaseClient.sql("INSERT INTO books(id, name, pages) VALUES(:id, :name, :pages ) ")
				.bind("id", id)
				.bind("name", book.getName())
				.bind("pages", book.getPages())
				.fetch().rowsUpdated().thenReturn(id);
	}
	
	public Mono<Long> createBook(Book book, final String authorName, final int age) {
		
		
		return databaseClient.sql("INSERT INTO books(id, name, pages) VALUES(:id, :name, :pages ) ")
				.bind("id", Utils.genId())
				.bind("name", book.getName())
				.bind("pages", book.getPages())
				.fetch().rowsUpdated();
	}
	
}
