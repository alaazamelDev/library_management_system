package com.maids.library_management_system.book.services;

import com.maids.library_management_system.book.entities.Book;
import com.maids.library_management_system.book.repositories.BookRepository;
import com.maids.library_management_system.book.requests.CreateBookRequest;
import com.maids.library_management_system.book.requests.UpdateBookRequest;
import com.maids.library_management_system.config.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "books_list")
    public List<Book> findAll() {
        Iterable<Book> books = bookRepository.findAll();
        return Streamable.of(books).toList();
    }

    public Book findOne(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public Book save(CreateBookRequest request) {

        // crate the entity
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .publicationYear(request.getPublication_year())
                .build();

        // save it
        return bookRepository.save(book);
    }

    public Book update(Integer id, UpdateBookRequest request) {
        // check if the book is existing.
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        // Update the fields if they are provided in the request
        if (request.getTitle() != null) {
            existingBook.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            existingBook.setAuthor(request.getAuthor());
        }
        if (request.getIsbn() != null) {
            existingBook.setIsbn(request.getIsbn());
        }
        if (request.getPublication_year() != null) {
            existingBook.setPublicationYear(request.getPublication_year());
        }

        // Save the updated entity
        return bookRepository.save(existingBook);
    }


    public void delete(Integer id) {
        // check if the book is existing.
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }
}
