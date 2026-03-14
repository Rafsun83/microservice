package com.example.self_management.controller;


import com.example.self_management.model.domain.Book;
import com.example.self_management.model.dto.book.CreateBookRequest;
import com.example.self_management.model.dto.book.UpdateBookRequest;
import com.example.self_management.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name="All Books", description = "Get all books")
public class BookController {
    private final BookService bookService;

    public  BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "All books", description = "All books for this system")
    @GetMapping
    public List<Book> getAllBooks(@ParameterObject Pageable pageable) {
        return bookService.getAllBook(pageable);
    }

    @Operation(summary = "Get book by Id", description = "Get book by Id through the API")
    @GetMapping("{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @Operation(summary = "Create Book", description = "Create all books")
    @PostMapping
    public Long createBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.createBook(createBookRequest);
    }

    @Operation(summary = "Update book", description = "Update book through the API")
    @PutMapping("{id}")
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        bookService.updateBook(id, updateBookRequest);
    }


    @Operation(summary = "Delete book", description = "Delete book through the API")
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
