package com.microserviceslab.graphql.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphqlRequestBody {
	
	private String query;
	private String operationName;
	private Map<String, Object> variables;
	

}
