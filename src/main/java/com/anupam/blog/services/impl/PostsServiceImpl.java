package com.anupam.blog.services.impl;

import com.anupam.blog.entities.Category;
import com.anupam.blog.entities.Post;
import com.anupam.blog.entities.User;
import com.anupam.blog.exceptions.ResourceNotFoundException;
import com.anupam.blog.payloads.PostPagedResponse;
import com.anupam.blog.payloads.PostsDto;
import com.anupam.blog.repositories.CategoryRepo;
import com.anupam.blog.repositories.PostsRepo;
import com.anupam.blog.repositories.UserRepo;
import com.anupam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service //This annotation is added to make it a component of spring container
public class PostsServiceImpl implements PostService {

    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo usersrepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostsDto createPost(PostsDto postDto,Integer userId,Integer categoryId) {
        User user=this.usersrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setPostedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post created = this.postsRepo.save(post);

        return this.modelMapper.map(created,PostsDto.class);
    }

    @Override
    public PostsDto updatePost(PostsDto postDto, Integer postId) {
        Post post=this.postsRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setImageName(postDto.getImageName());

        Post updated=this.postsRepo.save(post);

        return this.modelMapper.map(updated,PostsDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postsRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        this.postsRepo.delete(post);
    }

    @Override
    public PostPagedResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {

//        Sort sort;
//        if(direction.equalsIgnoreCase("asc"))
//            sort=Sort.by(sortBy).ascending();
//        else
//            sort=Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize,Sort.by("postId"));
        Page<Post> pagedPost= this.postsRepo.findAll(p);
        List<Post> posts=pagedPost.getContent();
        Set<PostsDto> allPosts = posts.stream().map( (post -> this.modelMapper.map(post,PostsDto.class))).collect(Collectors.toSet());

        PostPagedResponse postPagedResponse=new PostPagedResponse();
        postPagedResponse.setContent(allPosts);
        postPagedResponse.setPageNumber(pagedPost.getNumber());
        postPagedResponse.setNextPageNumber(pagedPost.getNumber()+1);
        postPagedResponse.setPageSize(pagedPost.getSize());
        postPagedResponse.setTotalPages(pagedPost.getTotalPages());
        postPagedResponse.setTotalElements(pagedPost.getNumberOfElements());
        postPagedResponse.setLastPage(pagedPost.isLast());

        return postPagedResponse;
    }

    @Override
    public PostsDto getPostById(Integer postId) {
        Post postById=this.postsRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));

        return this.modelMapper.map(postById,PostsDto.class);
    }

    @Override
    public Set<PostsDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        Set<Post> posts=this.postsRepo.findByCategory(category);

        Set<PostsDto> postsByCategory = posts.stream().map( (post -> this.modelMapper.map(post,PostsDto.class))).collect(Collectors.toSet());

        return postsByCategory;
    }

    @Override
    public Set<PostsDto> getPostByUser(Integer userId) {
        User user=this.usersrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users","UserId",userId));
        Set<Post> posts=this.postsRepo.findByUser(user);

        Set<PostsDto> postsByUser = posts.stream().map( (post -> this.modelMapper.map(post,PostsDto.class))).collect(Collectors.toSet());

        return postsByUser;
    }

    @Override
    public List<PostsDto> searchPostByTitle(String title) {
        List<Post> posts=this.postsRepo.searchPostByTitle("%"+title+"%");

        return posts.stream().map((post)-> this.modelMapper.map(post,PostsDto.class)).collect(Collectors.toList());

    }
}

