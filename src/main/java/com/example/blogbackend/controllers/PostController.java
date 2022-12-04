package com.example.blogbackend.controllers;

import com.example.blogbackend.models.Post;
import com.example.blogbackend.repos.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PostController {

    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postDao.findAll();
    }
    @GetMapping("/api/posts/{id}")
    public Post singlePost(@PathVariable long id) {
        return postDao.findById(id).get();
    }

}
