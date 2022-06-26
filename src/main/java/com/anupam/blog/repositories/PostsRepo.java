package com.anupam.blog.repositories;

import com.anupam.blog.entities.Category;
import com.anupam.blog.entities.Post;
import com.anupam.blog.entities.User;
import com.anupam.blog.payloads.PostsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostsRepo extends JpaRepository<Post,Integer> {

    Set<Post> findByUser(User user);

    Set<Post> findByCategory(Category category);
}
