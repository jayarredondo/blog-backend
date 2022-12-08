package com.example.blogbackend.controllers;

import com.example.blogbackend.models.User;
import com.example.blogbackend.repos.UserRepository;
//import com.example.blogbackend.security.CustomAuthManager;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    // This method is necessary for checking if the user is currently logged in.
    @GetMapping("/api/users/me")
    public Optional<User> getCurrentUser(OAuth2Authentication auth) {
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please log in.");
        }
        String username = auth.getName();
        User currentUser = userDao.findByUsername(username);

        return Optional.of(currentUser);
    }
}
