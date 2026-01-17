package com.microserviceslab.graphql.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microserviceslab.graphql.model.GraphqlRequestBody;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class GraphqlController {
	
//	@Autowired(required=true)
	private final GraphQL graphql;
	
	@Autowired
    public GraphqlController(GraphQL graphQL) {
        this.graphql = graphQL;
    }

	
	@PostMapping(value = "graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Map<String, Object>> execute(@RequestBody GraphqlRequestBody body) {
		return Mono.fromCompletionStage(graphql.executeAsync(ExecutionInput.newExecutionInput().query(body.getQuery())
				.operationName(body.getOperationName()).variables(body.getVariables()).build()))
				.map(ExecutionResult::toSpecification);
	}

}
