package com.example.self_management.service;

import com.example.self_management.mapper.BlogMapper;
import com.example.self_management.model.domain.Blog;
import com.example.self_management.model.dto.blog.CreateBlogRequest;
import com.example.self_management.model.dto.blog.UpdateBlogRequest;
import com.example.self_management.persistence.entity.BlogEntity;
import com.example.self_management.persistence.repository.BlogRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BlogService {

    private final  BlogRepository blogRepository;
    private final BlogMapper  blogMapper;


    public  BlogService(BlogRepository blogRepository, BlogMapper blogMapper){
        this.blogRepository =  blogRepository;
        this.blogMapper = blogMapper;
    }

    public List<Blog> getALlBlogs(Pageable pageable){
        List<BlogEntity> blogEntityList = blogRepository.findAll(pageable).getContent();
        return blogEntityList.stream().map(blogMapper::entityToDomain).toList();
    }

    public Blog getBlogById(Long id){
        BlogEntity blogEntity = blogRepository.findById(id).orElse(null);
        return blogMapper.entityToDomain(blogEntity);
    }

    public Long createBlog(CreateBlogRequest blogRequest){
        var entityToSave = blogMapper.createBlogRequestToEntity(blogRequest);
        var savedEntity = blogRepository.save(entityToSave);
        return savedEntity.getId();
    }

    public void updateBlog(Long id, UpdateBlogRequest updateBlogRequest){
        var blogEntity = blogRepository.findById(id).get();
        var updateBlog = blogMapper.updateBlogRequestToEntity(updateBlogRequest, blogEntity);
        blogRepository.save(updateBlog);
    }

    public void deleteBlog(Long id){
        blogRepository.deleteById(id);
    }

}
