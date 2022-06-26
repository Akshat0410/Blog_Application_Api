package com.anupam.blog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title")
    private String title;

    @Column( name = "post_description" , length =10000)
    private String description;

    private String imageName;

    private Date postedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
