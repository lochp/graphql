package com.microserviceslab.graphql.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@ComponentScan
public class GraphQlConfiguration {

	@Bean
	public ConnectionFactoryInitializer connectionFactoryInitializer( ConnectionFactory factory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(factory);
		ResourceDatabasePopulator popular = new ResourceDatabasePopulator( new ClassPathResource("schema.sql"), new ClassPathResource("data.sql"));
		initializer.setDatabaseCleaner(popular);
		return initializer;
	}
	
}
