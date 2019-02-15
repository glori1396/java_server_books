package com.example.demo.bookcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookServiceDef implements BookService {

    @Autowired
    private BookDAO bookDAO;

    ArrayList<Book> books = new ArrayList();

    @Override
    @Transactional()
    public Book addBook(Book book) {
        if(books.size()==0){getBooks();}
        if(!book.getName().equals("") && !book.getAuthor().equals("")) {
            Optional<Book> any = books.stream().filter(item -> item.getName().equals(book.getName()) || item.getAuthor().equals(book.getAuthor())).findAny();
            if (any.isPresent()) {
                System.out.println("Ya existe el libro.");
                return null;
            } else {
                System.out.println("Estoy agregando el libro");
                this.bookDAO.save(book);
                this.books.add(book);
            }
            return book;
        }else{
            System.out.println("retorno nulo");
            return null;
        }
    }

    @Override
    public Book deleteBook(int id) {
        Optional<Book> result = books.stream().filter(book -> book.getId()==(id)).findAny();
        if(result.isPresent()) {
            this.books.remove(result.isPresent() ? result.get() : null);
            this.bookDAO.deleteById(result.get().getId());
            return result.get();
        }else{
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> result = books.stream().filter(item -> item.getId() == (book.getId())).findAny();
        Optional<Book> exist = books.stream().filter(item -> item.getName().equals(book.getName()) || item.getAuthor().equals(book.getAuthor())).findAny();
        if(result.isPresent() || !exist.isPresent()) {
            this.books.set(books.indexOf(result.get()), result.get());
            return bookDAO.save(result.get());
        }else{
            return null;
        }
    }

    @Override
    public Optional<Book> getBook(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public Object getBooks() {
        this.books = (ArrayList) bookDAO.findAll();
        return books;
    }
}
