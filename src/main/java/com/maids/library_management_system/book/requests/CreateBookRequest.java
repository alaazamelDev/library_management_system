package com.maids.library_management_system.book.requests;

import com.maids.library_management_system.book.constraints.UniqueTitle;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank(message = "title is required")
    @NotNull(message = "Invalid title: title is NULL")
    @Size(max = 50, message = "title must be at most 50 characters long")
    @UniqueTitle
    private String title;

    @NotBlank(message = "author is required")
    @NotNull(message = "Invalid author: author is NULL")
    @Size(max = 50, message = "author must be at most 50 characters long")
    private String author;

    @NotBlank(message = "isbn is required")
    @NotNull(message = "Invalid isbn: isbn is NULL")
    @Size(max = 100, message = "isbn must be at most 100 characters long")
    private String isbn;

    @NotNull(message = "Invalid publication_year: publication_year is NULL")
    @Digits(integer = 4, fraction = 0, message = "publication year must be a 4-digit number")
    @Positive(message = "publication year must be positive")
    private Integer publication_year;
}
