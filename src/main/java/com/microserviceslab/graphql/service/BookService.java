package com.microserviceslab.graphql.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microserviceslab.graphql.model.Book;
import com.microserviceslab.graphql.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class BookService {
	
	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepo;

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
	
	public DataFetcher<CompletableFuture<Long>> createBook(){
		return evn -> {
			final String name = evn.getArgument("name");
			final Integer pages = evn.getArgument("pages");
			return bookRepo.createBook(new Book(null, name, pages)).toFuture();
		};
	}
	
}
