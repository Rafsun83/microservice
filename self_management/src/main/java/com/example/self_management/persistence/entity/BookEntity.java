package com.example.self_management.persistence.entity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String about;
    private String category;
    private String coverImage;
    private Date uploadDate;

    public BookEntity() {}

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public Long getId() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUploadDate() {
        return uploadDate;
    }
    public  void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
