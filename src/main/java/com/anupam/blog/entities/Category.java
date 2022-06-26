package com.anupam.blog.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "title" , length = 100, nullable = false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	Set<Post> post=new HashSet<Post>();
	
    
}
