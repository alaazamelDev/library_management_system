package com.maids.library_management_system.book.responses;

import com.maids.library_management_system.book.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {


    private int id;
    private String title;
    private String author;
    private Integer publication_year;
    private String isbn;

    public static BookResponse from(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publication_year(book.getPublicationYear())
                .build();
    }

    public static List<BookResponse> from(List<Book> books) {
        return books.stream().map(BookResponse::from).toList();
    }
}
