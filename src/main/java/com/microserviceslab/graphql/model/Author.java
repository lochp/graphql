package com.microserviceslab.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(of = {"id", "name", "age", "bookId"})
@Table("authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	@Id
	private Integer id;
	private String name;
	private Integer age;
	
	@Column("book_id")
	private Integer bookId;

}
