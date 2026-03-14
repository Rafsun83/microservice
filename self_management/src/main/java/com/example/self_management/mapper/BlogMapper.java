package com.example.self_management.mapper;

import com.example.self_management.model.domain.Blog;
import com.example.self_management.model.dto.blog.CreateBlogRequest;
import com.example.self_management.model.dto.blog.UpdateBlogRequest;
import com.example.self_management.persistence.entity.BlogEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BlogMapper {

    public Blog entityToDomain(BlogEntity blogEntity){
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogEntity,blog);
        return blog;
    }

    public BlogEntity createBlogRequestToEntity(CreateBlogRequest createBlogRequest) {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setPublishedDate(new Date());
        BeanUtils.copyProperties(createBlogRequest, blogEntity);
        return blogEntity;
    };

    public BlogEntity updateBlogRequestToEntity(UpdateBlogRequest updateBlogRequest,  BlogEntity blogEntity) {
            blogEntity.setTitle(updateBlogRequest.title());
            blogEntity.setContent(updateBlogRequest.content());
            blogEntity.setThumbnail(updateBlogRequest.thumbnail());
            blogEntity.setUpdateDate(new Date());
            return blogEntity;
    }
}
