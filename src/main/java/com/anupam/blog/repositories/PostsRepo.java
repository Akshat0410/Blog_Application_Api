package com.anupam.blog.repositories;

import com.anupam.blog.entities.Category;
import com.anupam.blog.entities.Post;
import com.anupam.blog.entities.User;
import com.anupam.blog.payloads.PostsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PostsRepo extends JpaRepository<Post,Integer> {

    Set<Post> findByUser(User user);

    Set<Post> findByCategory(Category category);

    @Query("Select p from Post p where p.title like :key")
    List<Post> searchPostByTitle(@Param("key") String title);
}
