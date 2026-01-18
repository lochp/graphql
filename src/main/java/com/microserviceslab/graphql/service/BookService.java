package com.microserviceslab.graphql.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microserviceslab.graphql.model.Author;
import com.microserviceslab.graphql.model.Book;
import com.microserviceslab.graphql.repository.AuthorRepository;
import com.microserviceslab.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class BookService {
	
	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorRepository authorRepo;

	public DataFetcher<CompletableFuture<Book>> getBook(){
		return env -> {
			Integer bookId = env.getArgument("id");
			return bookRepo.getBook(bookId).toFuture();
		};
	}
	
	public DataFetcher<CompletableFuture<List<Book>>> getBooks(){
		return env -> {
			return bookRepo.getBooks().collectList().toFuture();
		};
	}
	
	public DataFetcher<CompletableFuture<Integer>> createBook(){
		return evn -> {
			final String bookName = evn.getArgument("bookName");
			final Integer pages = evn.getArgument("pages");
			Book book = new Book(null, bookName, pages);
			
			final String authorName = evn.getArgument("authorName");
			final Integer age = evn.getArgument("age");
			return bookRepo.createBook(book)
					.flatMap(bookId -> authorRepo.createAuthor(new Author(null, authorName, age, bookId)))
					.map(authorId -> authorId).toFuture();
		};
	}
	
}
