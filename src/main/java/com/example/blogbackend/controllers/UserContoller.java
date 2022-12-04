package com.example.blogbackend.controllers;

import com.example.blogbackend.models.User;
import com.example.blogbackend.repos.UserRepository;
//import com.example.blogbackend.security.CustomAuthManager;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class UserContoller {

    private final PasswordEncoder encoder;
//    private CustomAuthManager authManager;
    private final UserRepository userDao;

    public UserContoller(PasswordEncoder encoder, UserRepository userDao) {
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @GetMapping("/api/users")
    public List<User> allUsers() {
        return userDao.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userDao.findById(id).get();
    }

    @PostMapping("/api/users/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
