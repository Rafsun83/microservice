package com.example.self_management.model.domain;
import java.util.Date;

public class Blog {


    private Long id;
    private String title;
    private String content;
    private String category ;
    private Date publishedDate;
    private Date updateDate;
    private String thumbnail;
    private String authorName;

    public Blog() {}

    public Blog(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
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
