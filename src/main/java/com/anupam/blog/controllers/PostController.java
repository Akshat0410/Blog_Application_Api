package com.anupam.blog.controllers;

import com.anupam.blog.entities.Post;
import com.anupam.blog.payloads.PostsDto;
import com.anupam.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    private PostService postService;
    //create user

    @PostMapping("/users/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostsDto> createPost(@RequestBody PostsDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostsDto created = this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostsDto>(created, HttpStatus.CREATED);

    }
}
