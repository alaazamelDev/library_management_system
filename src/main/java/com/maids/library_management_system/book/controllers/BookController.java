package com.maids.library_management_system.book.controllers;

import com.maids.library_management_system.book.entities.Book;
import com.maids.library_management_system.book.requests.CreateBookRequest;
import com.maids.library_management_system.book.requests.UpdateBookRequest;
import com.maids.library_management_system.book.responses.BookResponse;
import com.maids.library_management_system.book.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookResponse> bookResponses = BookResponse.from(books);
        return ResponseEntity.ok(bookResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(BookResponse.from(bookService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@Valid @RequestBody CreateBookRequest request) {
        return ResponseEntity.ok(BookResponse.from(bookService.save(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") Integer id,@Valid @RequestBody UpdateBookRequest request) {
        return ResponseEntity.ok(BookResponse.from(bookService.update(id, request)));
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.delete(id);
    }

}
