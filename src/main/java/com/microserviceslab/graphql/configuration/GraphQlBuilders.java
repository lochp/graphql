package com.microserviceslab.graphql.configuration;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class GraphQlBuilders {

//	@Autowired
//	private BookService bookService;
//	
//	@Bean("runtimeWiring")
//	public RuntimeWiring getRuntimeWiring(){
//		
//		return RuntimeWiring.newRuntimeWiring()
//				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBook", bookService.getBook()))
//				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooks", bookService.getBooks()))
//				.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("createBook", bookService.createBook()))
//				.build();
//	}
//	
//	public static final List<String> methods = List.of("getBook", "getBooks", "createBook");
//	
//	public void handle() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
//		// Find the method by name and parameter types
//	    Method method = BookService.class.getMethod("createBook", String.class, int.class);
//	    List<Method> methodCallers = methods.stream().map(e -> {
//			try {
//				return BookService.class.getMethod(e);
//			} catch (NoSuchMethodException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (SecurityException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			return null;
//		}).filter(e -> e != null).collect(Collectors.toList());
//	    
//	    Map<String, Method> map = methods.stream().collect(name -> name, name -> BookService.class.getMethod(name));
//	    
//	    List<Builder> builders = methods.stream().map(name -> {
//	    	return TypeRuntimeWiring.newTypeWiring("Query").dataFetcher(name, (Method)(map.get(name)).invoke(bookService));
//	    }).collect(Collectors.toList());
//	    
//	    // Invoke the method with arguments
//	    Object result = method.invoke(bookService, "My new book", 101);
//
//	}
	
}
