package com.example.demo.bookcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
    @RequestMapping(value = "/v2")
public class Controller {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/book")
    public ResponseEntity getBooks(@RequestHeader("auth-token") String token ){
        ArrayList books = (ArrayList) bookService.getBooks();
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping(value = "/book/{idBook}")
    public ResponseEntity getBook(@RequestHeader("auth-token") String token, @PathVariable int idBook){
        Optional<Book> findBook = bookService.getBook(idBook);
        return new ResponseEntity(findBook.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/book")
        public ResponseEntity addBook(@Validated(New.class) @RequestBody Book book, @RequestHeader("auth-token") String token){
            Book newBook = bookService.addBook(new Book(book.getName(), book.getAuthor(), book.getDescription()));
            return new ResponseEntity(newBook, HttpStatus.OK);
    }


    @PostMapping(value = "/user")
    public ResponseEntity addUser(@Validated(New.class) @RequestBody User user, @RequestHeader("auth-token") String token){
        System.out.println("------------ entro");
        User newUser= userService.addUser(new User(user.getName(), user.getUsername()));
        return new ResponseEntity(newUser, HttpStatus.OK);
    }


}
