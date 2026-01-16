package com.microserviceslab.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(of = {"id", "name", "pages"})
@Table("books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	private Integer id;
	private String name;
	private Integer pages;
	
}
