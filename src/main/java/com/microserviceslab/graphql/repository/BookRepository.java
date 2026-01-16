package com.microserviceslab.graphql.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.microserviceslab.graphql.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("bookRepository")
public class BookRepository {

	@Autowired
	private DatabaseClient databaseClient;
	
	public Mono<Book> getBook(int id) {
		return databaseClient.sql(String.format("SELECT * FROM books WHERE id = %d", id)).map(row -> new Book(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class))).one();
	}
	
	public Flux<Book> getBooks() {
		return databaseClient.sql("SELECT * FROM books").map(row -> new Book(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class))).all();
	}
	
	public Mono<Long> createBook(Book book) {
		UUID bookId = UUID.randomUUID();
		return databaseClient.sql("INSERT INTO books(id, name, pages) VALUES(:id, :name, :pages ) ")
				.bind("id", bookId.variant())
				.bind("name", book.getName())
				.bind("pages", book.getPages())
				.fetch().rowsUpdated();
	}
	
}
