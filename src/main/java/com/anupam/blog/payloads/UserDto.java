package com.anupam.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private Integer user_id;
	
	@NotEmpty
	@Size(min=4,message = "Username must be minimum 4 character long")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min=8,max=15,message = "Invalid password")
	private String password;
	
	@NotEmpty
	private String about;

}
