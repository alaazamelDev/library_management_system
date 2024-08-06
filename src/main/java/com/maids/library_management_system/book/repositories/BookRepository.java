package com.maids.library_management_system.book.repositories;

import com.maids.library_management_system.book.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByTitle(String isbn);
}
