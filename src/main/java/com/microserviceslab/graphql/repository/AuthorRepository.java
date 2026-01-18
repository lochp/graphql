package com.microserviceslab.graphql.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.microserviceslab.graphql.model.Author;
import com.microserviceslab.graphql.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository("authorRepository")
public class AuthorRepository {
	
	@Autowired
	private DatabaseClient databaseClient;
	
	public Mono<Integer> createAuthor(Author author) {
		Integer id = Utils.genId();
		return databaseClient.sql("INSERT INTO authors(id, name, age, book_id) VALUES(:id, :name, :age, :bookId ) ")
				.bind("id", id)
				.bind("name", author.getName())
				.bind("age", author.getAge())
				.bind("bookId", author.getBookId())
				.fetch().rowsUpdated().thenReturn(id);
	}
	
	public Mono<Author> getAuthor(final int id) {
		return databaseClient.sql(String.format("SELECT * FROM authors WHERE id = %d", id))
				.map(row -> new Author(row.get("id", Integer.class), row.get("name", String.class), row.get("age", Integer.class), row.get("book_id", Integer.class))).one();
	}
	
	public Mono<Author> getAuthorByBookId(final int bookId) {
		return databaseClient.sql(String.format("SELECT * FROM authors WHERE book_id = %d", bookId))
				.map(row -> new Author(row.get("id", Integer.class), row.get("name", String.class), row.get("age", Integer.class), row.get("book_id", Integer.class))).one();
	}
	
	public Flux<Author> getAuthors() {
		return databaseClient.sql("SELECT * FROM authors")
				.map(row -> new Author(row.get("id", Integer.class), row.get("name", String.class), row.get("pages", Integer.class), row.get("book_id", Integer.class))).all();
	}

}
