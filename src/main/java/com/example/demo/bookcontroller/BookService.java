package com.example.demo.bookcontroller;

import java.util.Optional;

public interface BookService {
    Book addBook(Book book);
    Book deleteBook(int id);
    Book updateBook(Book book);
    Optional<Book> getBook(int id);
    Object getBooks();
}