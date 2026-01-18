package com.microserviceslab.graphql.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microserviceslab.graphql.model.Author;
import com.microserviceslab.graphql.model.Book;
import com.microserviceslab.graphql.repository.AuthorRepository;

import graphql.schema.DataFetcher;
import reactor.core.publisher.Mono;

@Service("authorService")
public class AuthorService {
	
	@Autowired
	@Qualifier("authorRepository")
	private AuthorRepository authorRepo;
	
	public Mono<String> createAuthor(String authorName, int age, int bookId){
		Author author = new Author(null, authorName, age, bookId);
		return authorRepo.createAuthor(author).map(Object::toString);
	}
	
	public DataFetcher<CompletableFuture<Author>> getAuthorDataFetcher(){
		return evn -> {
			Book book = evn.getSource();
			return authorRepo.getAuthorByBookId(book.getId()).toFuture();
		};
	}
	
}
