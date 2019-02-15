package com.example.demo.bookcontroller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDAO extends JpaRepository<Book, Integer> {

}
