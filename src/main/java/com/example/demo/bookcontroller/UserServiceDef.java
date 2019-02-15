package com.example.demo.bookcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserServiceDef implements UserService {

    @Autowired
    private UserDAO userDAO;

    ArrayList<User> users = new ArrayList();


    @Override
    @Transactional()
    public User addUser(User user) {
        System.out.println("Estoy agregando el usuario");
        this.userDAO.save(user);
        this.users.add(user);

        return user;
    }

    
    public ArrayList getUsers(){
        return (ArrayList) userDAO.findAll();
    }
}