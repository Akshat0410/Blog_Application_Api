package com.anupam.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anupam.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
