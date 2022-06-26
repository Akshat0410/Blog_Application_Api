package com.anupam.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.blog.payloads.ApiResponse;
import com.anupam.blog.payloads.UserDto;
import com.anupam.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> creatUser(@Valid @RequestBody UserDto userDto){
	
		UserDto createdUser=this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
	}
	
	
	
	//Read User
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	
	
	
	
	//Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updatedUser=this.userService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	
	//Delete User
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		
	  this.userService.deleteUSer(userId);
	  
	  return new ResponseEntity<ApiResponse>(new ApiResponse("User Successfully deleted",true),HttpStatus.OK);
	  	
	}
  
}
