package com.anupam.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anupam.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	

}
