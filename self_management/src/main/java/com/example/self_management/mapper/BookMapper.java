package com.example.self_management.mapper;

import com.example.self_management.model.domain.Book;
import com.example.self_management.model.dto.book.CreateBookRequest;
import com.example.self_management.model.dto.book.UpdateBookRequest;
import com.example.self_management.persistence.entity.BookEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BookMapper {

    public Book entityToBookDomain(BookEntity bookEntity) {
        Book book = new Book();
        BeanUtils.copyProperties(bookEntity,book);
        return book;
    }

    public BookEntity createBookRequestToEntity(CreateBookRequest createBookRequest) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setUploadDate(new Date());
        BeanUtils.copyProperties(createBookRequest,bookEntity);
        return bookEntity;
    }

    public BookEntity updateBookRequestToEntity(UpdateBookRequest updateBookRequest, BookEntity bookEntity) {
        bookEntity.setTitle(updateBookRequest.title());
        bookEntity.setAbout(updateBookRequest.about());
        bookEntity.setCoverImage(updateBookRequest.coverImage());
        bookEntity.setUploadDate(new Date());
        return bookEntity;

    }
}
