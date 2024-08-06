package com.maids.library_management_system.book.requests;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {

    @Size(max = 50, message = "title must be at most 50 characters long")
    private String title;

    @Size(max = 50, message = "author must be at most 50 characters long")
    private String author;

    @Size(max = 100, message = "isbn must be at most 100 characters long")
    private String isbn;

    @Digits(integer = 4, fraction = 0, message = "publication year must be a 4-digit number")
    @Positive(message = "publication year must be positive")
    private Integer publication_year;
}
