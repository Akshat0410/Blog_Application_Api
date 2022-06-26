package com.anupam.blog.services.impl;

import com.anupam.blog.entities.Category;
import com.anupam.blog.entities.Post;
import com.anupam.blog.entities.User;
import com.anupam.blog.exceptions.ResourceNotFoundException;
import com.anupam.blog.payloads.PostsDto;
import com.anupam.blog.repositories.CategoryRepo;
import com.anupam.blog.repositories.PostsRepo;
import com.anupam.blog.repositories.UserRepo;
import com.anupam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service //This annotation is added to make it a component of spring container
public class PostsServiceImpl implements PostService {

    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo usersrepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostsDto createPost(PostsDto postDto,Integer userId,Integer categoryId) {
        User user=this.usersrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setPostedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post created = this.postsRepo.save(post);

        return this.modelMapper.map(created,PostsDto.class);
    }

    @Override
    public PostsDto updatePost(PostsDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public Set<PostsDto> getAllPost() {
        return null;
    }

    @Override
    public PostsDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public Set<PostsDto> getPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public Set<PostsDto> getPostByUser(Integer userId) {
        return null;
    }

    @Override
    public Set<PostsDto> getPostBySearch(String query) {
        return null;
    }
}
