package com.anupam.blog.services;

import com.anupam.blog.entities.Post;
import com.anupam.blog.payloads.PostsDto;

import java.util.Set;

public interface PostService {

    //create post

    Post createPost(PostsDto postDto);

    //update post

    Post updatePost(PostsDto postDto, Integer postId);

    //delete post

    void deletePost(Integer postId);

    //get all posts

    Set<Post> getAllPost();

    //get single post

    Post getPostById(Integer postId);

    //get category wise posts

    Set<Post> getPostByCategory(Integer categoryId);

    //get user wise posts

    Set<Post> getPostByUser(Integer userId);

    //get posts by search

    Set<Post> getPostBySearch(String query);


}
