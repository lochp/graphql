package com.microserviceslab.graphql;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.io.ClassPathResource;

import com.microserviceslab.graphql.service.BookService;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import graphql.schema.idl.errors.SchemaProblem;

@SpringBootApplication
@ComponentScans(value = { @ComponentScan })
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}
	
	@Autowired
	private BookService bookService;
	
	@Bean
	public GraphQL graphQl() throws SchemaProblem, IOException {
		SchemaParser schemaParser = new SchemaParser();
		ClassPathResource schema = new ClassPathResource("schema.graphql");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema.getInputStream());
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
		.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBook", bookService.getBook()))
		.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooks", bookService.getBooks()))
		.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createBook", bookService.createBook()))
		.build();
		
		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema graphQlSchema = generator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		return GraphQL.newGraphQL(graphQlSchema).build();
	}
	
}
