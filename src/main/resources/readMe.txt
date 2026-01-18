**QUERY**
http://localhost:8080/graphql

{
  "query": "query($bookId: Int){ getBook(id: $bookId) { id name pages } }",
  "variables": {
    "bookId": 1
  }
}

{
  "query": "query { getBooks { id name pages author {id name age } } }",
  "variables": {}
}

{
  "query": "query { getBook(id: 971309300) { id name pages author {id name age } } }",
  "variables": {}
}

**MUTATION**

{
  "query": "mutation { createBook(name: \"My new book\", pages: 101) }",
  "variables": {}
}

{
  "query": "mutation { createBook(bookName: \"My new book\", pages: 101, authorName: \"Loc\", age: 35) }",
  "variables": {}
}