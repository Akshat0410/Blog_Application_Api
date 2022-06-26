package com.anupam.blog.controllers;

import com.anupam.blog.entities.Post;
import com.anupam.blog.payloads.PostsDto;
import com.anupam.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    private PostService postService;


    //create user
    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostsDto> createPost(@RequestBody PostsDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {

        PostsDto created = this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostsDto>(created, HttpStatus.CREATED);

    }

    //get post by users

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<Set<PostsDto>> getPostByUser(@PathVariable Integer userId){
        Set<PostsDto> posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<Set<PostsDto>>(posts,HttpStatus.OK);
    }

    //get post by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<Set<PostsDto>> getPostByCategory(@PathVariable Integer categoryId){
        Set<PostsDto> posts=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<Set<PostsDto>>(posts,HttpStatus.OK);
    }

    //get all posts

    @GetMapping("/posts")
    public ResponseEntity<Set<PostsDto>> getAllPosts(){
        Set<PostsDto> posts=this.postService.getAllPost();
        return new ResponseEntity<Set<PostsDto>>(posts,HttpStatus.OK);
    }

    //get post by Id

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostsDto> getPostById(@PathVariable Integer postId){
        PostsDto postById=this.postService.getPostById(postId);

        return new ResponseEntity<PostsDto>(postById,HttpStatus.OK);
    }


}
