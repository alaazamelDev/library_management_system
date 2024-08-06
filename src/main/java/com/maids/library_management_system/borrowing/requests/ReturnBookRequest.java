package com.maids.library_management_system.borrowing.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBookRequest {

    @NotNull(message = "Invalid book id: book id is NULL")
    private Integer bookId;

    @NotNull(message = "Invalid patron id: patron id is NULL")
    private Integer patronId;

}
