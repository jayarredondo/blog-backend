package com.example.blogbackend.controllers;

import com.example.blogbackend.models.Post;
import com.example.blogbackend.models.User;
import com.example.blogbackend.models.UserRole;
import com.example.blogbackend.repos.PostRepository;
import com.example.blogbackend.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class PostController {

    private PostRepository postDao;
    private UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postDao.findAll();
    }
    @GetMapping("/api/posts/{id}")
    public Post singlePost(@PathVariable long id) {
        return postDao.findById(id).get();
    }

//    @PostMapping("/api/posts/create")
    // PreAuthorize will allow us to give different authorities to certain enpoints.
    // Here, a user and admin can both create posts.
//    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
//    public void createPost(@RequestBody Post newPost, OAuth2Authentication auth) {
//        // check to see if values are null or blank.
//        if(newPost.getTitle() == null || newPost.getTitle().length() < 1) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title cannot be blank!");
//        }
//        if(newPost.getBody() == null || newPost.getBody().length() < 1) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Content cannot be blank!");
//        }
//
//        String username = auth.getName();
//        User author = userDao.findByUsername(username);
//        newPost.setAuthor(author);
//
//        postDao.save(newPost);
//
//    }

//    @PatchMapping("/api/posts/{id}/edit")
//    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
//    public void updatePost(@RequestBody Post updatedPost, @PathVariable long id, OAuth2Authentication auth) {
//        Optional<Post> optionalPost = postDao.findById(id);
//
//        if(optionalPost.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " not found");
//        }
//        Post originalPost = optionalPost.get();
//
//        String username = auth.getName();
//        User loggedInUser = userDao.findByUsername(username);
//
//        if(loggedInUser.getRole() != UserRole.ADMIN && originalPost.getAuthor().getId() != loggedInUser.getId()) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not yo post!");
//        }
//
//        originalPost.setAuthor(loggedInUser);
//        originalPost.setBody(updatedPost.getBody());
//        originalPost.setTitle(updatedPost.getTitle());
//
//        postDao.save(originalPost);
//
//    }

//    @DeleteMapping("/api/posts/{id}/delete")
//    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
//    public void deletePost(@PathVariable long id, OAuth2Authentication auth) {
//        String username = auth.getName();
//        User loggedInUser = userDao.findByUsername(username);
//
//        // check to see if the post is actually there before deleting it.
//        Optional<Post> optionalPost = postDao.findById(id);
//        if(optionalPost.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id " + id + " not found");
//        }
//        // grab the original post from the optional and check the logged in user
//        Post originalPost = optionalPost.get();
//
//        // admin can delete anyone's post. author of the post can delete only their posts
//        if(loggedInUser.getRole() != UserRole.ADMIN && originalPost.getAuthor().getId() != loggedInUser.getId()) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not yo post!");
//        }
//
//        postDao.deleteById(id);
//
//    }

}
