package com.example.demo.bookcontroller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDAO extends MongoRepository<User, String> {

}
