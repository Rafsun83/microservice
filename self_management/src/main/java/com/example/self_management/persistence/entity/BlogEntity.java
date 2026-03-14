package com.example.self_management.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="blogs")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String category ;
    private Date publishedDate;
    private Date updateDate;
    private String thumbnail;
    private String authorName;

    public BlogEntity() {}

    public BlogEntity(String title, String content, String category, String authorName, String thumbnail) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.thumbnail = thumbnail;
        this.authorName = authorName;

    }

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
