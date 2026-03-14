package com.example.self_management.service;

import com.example.self_management.mapper.BookMapper;
import com.example.self_management.model.domain.Book;
import com.example.self_management.model.dto.book.CreateBookRequest;
import com.example.self_management.model.dto.book.UpdateBookRequest;
import com.example.self_management.persistence.entity.BookEntity;
import com.example.self_management.persistence.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper  bookMapper;

    public  BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBook(Pageable pageable) {
        List<BookEntity> bookEntityList = bookRepository.findAll(pageable).getContent();
        return bookEntityList.stream().map(bookMapper:: entityToBookDomain).toList();
    }

    public Book getBookById(Long id){
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        return bookMapper.entityToBookDomain(bookEntity);
    }

    public Long createBook(CreateBookRequest createBookRequest){
        var entityToSave = bookMapper.createBookRequestToEntity(createBookRequest);
        var saveBookEntity = bookRepository.save(entityToSave);
        return saveBookEntity.getId();
    }

    public void updateBook(Long id, UpdateBookRequest updateBookRequest){
        var bookEntity = bookRepository.findById(id).get();
        var updateBook = bookMapper.updateBookRequestToEntity(updateBookRequest, bookEntity);
        bookRepository.save(updateBook);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}
