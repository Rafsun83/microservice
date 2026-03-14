package com.example.self_management.controller;

import com.example.self_management.model.domain.Blog;
import com.example.self_management.model.dto.blog.CreateBlogRequest;
import com.example.self_management.model.dto.blog.UpdateBlogRequest;
import com.example.self_management.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@Tag(name = "Blogs", description = "Get API for all blogs")
public class BlogController {

    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Operation(summary = "Get all blogs", description = "Get all blogs from the system")
    @GetMapping
    public List<Blog> getAllBlogs(@ParameterObject Pageable  pageable){
        return blogService.getALlBlogs(pageable);
    }

    @Operation(summary = "Get blog through the id")
    @GetMapping("{id}")
    public Blog getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @Operation(summary = "Create blogs", description = "Blog creation API")
    @PostMapping
    public Long createBlog(@RequestBody CreateBlogRequest createBlogRequest){
        return blogService.createBlog(createBlogRequest);
    }

    @Operation(summary = "Update Blog", description = "Update any types of blog by this api")
    @PutMapping("{id}")
    public void updateBlog(@PathVariable Long id, @RequestBody UpdateBlogRequest updateBlogRequest){
        blogService.updateBlog(id, updateBlogRequest);
    }


    @Operation(summary = "Delete blog", description = "Delete blog through the API")
    @DeleteMapping("{id}")
    public void deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
    }
}
