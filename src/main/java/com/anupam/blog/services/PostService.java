package com.anupam.blog.services;

import com.anupam.blog.entities.Post;
import com.anupam.blog.payloads.PostsDto;

import java.util.Set;

public interface PostService {

    //create post

    PostsDto createPost(PostsDto postDto,Integer userId,Integer categoryId);

    //update post

    PostsDto updatePost(PostsDto postDto, Integer postId);

    //delete post

    void deletePost(Integer postId);

    //get all posts

    Set<PostsDto> getAllPost();

    //get single post

    PostsDto getPostById(Integer postId);

    //get category wise posts

    Set<PostsDto> getPostByCategory(Integer categoryId);

    //get user wise posts

    Set<PostsDto> getPostByUser(Integer userId);

    //get posts by search

    Set<PostsDto> getPostBySearch(String query);


}
