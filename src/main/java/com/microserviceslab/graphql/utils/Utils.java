package com.microserviceslab.graphql.utils;

public class Utils {
	
	public static int genId() {
		return Integer.parseInt(String.valueOf(java.time.Instant.now().getNano()));
	}

}
