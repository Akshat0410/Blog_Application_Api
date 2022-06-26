package com.anupam.blog.services;

import java.util.List;

import com.anupam.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer user_id);
	
	UserDto getUserById(Integer user_id);
	
	List<UserDto> getAllUser();
	
	void deleteUSer(Integer user_id);
	
	

}
