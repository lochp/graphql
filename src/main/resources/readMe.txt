**QUERY**
http://localhost:8080/graphql

{
  "query": "query($bookId: Int){ getBook(id: $bookId) { id name pages } }",
  "variables": {
    "bookId": 1
  }
}

{
  "query": "query { getBooks { id name pages } }",
  "variables": {}
}

**MUTATION**

{
  "query": "mutation { createBook(name: \"My new book\", pages: 101) }",
  "variables": {}
}