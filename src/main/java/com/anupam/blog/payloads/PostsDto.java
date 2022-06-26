package com.anupam.blog.payloads;

import com.anupam.blog.entities.Category;
import com.anupam.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PostsDto {

    private String title;

    private String description;

}
