package com.anupam.blog.services.impl;

import java.util.List;


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupam.blog.entities.User;
import com.anupam.blog.exceptions.ResourceNotFoundException;
import com.anupam.blog.payloads.UserDto;
import com.anupam.blog.repositories.UserRepo;
import com.anupam.blog.services.UserService;

@Service //This annotation is added to make it a component of spring container
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		//Todo These conversions can be done using model mapper library which I will do later
		User savedUser = userRepo.save(user);
		
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer user_id) {

		User user=this.userRepo.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id" , user_id));
		
		user.setUser_id(userDto.getUser_id());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer user_id) {
		
		User user=this.userRepo.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id" , user_id));
		
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users= this.userRepo.findAll();
		
		List<UserDto> userDto=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDto;
		
		
	}

	@Override
	public void deleteUSer(Integer user_id) {
		
		User user=this.userRepo.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id" , user_id));	
		
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
//		user.setUser_id(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getUser_id());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
		
		
	}

}
