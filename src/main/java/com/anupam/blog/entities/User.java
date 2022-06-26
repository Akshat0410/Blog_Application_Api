package com.anupam.blog.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "users")
@NoArgsConstructor
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer user_id;
	private String name;
	private String email;
	private String password;
	private String about;


	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	Set<Post> post=new HashSet<Post>();
	
}
