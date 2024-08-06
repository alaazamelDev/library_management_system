package com.maids.library_management_system.borrowing.controllers;

import com.maids.library_management_system.borrowing.entities.BorrowingRecord;
import com.maids.library_management_system.borrowing.requests.BorrowBookRequest;
import com.maids.library_management_system.borrowing.requests.ReturnBookRequest;
import com.maids.library_management_system.borrowing.responses.BorrowingRecordResponse;
import com.maids.library_management_system.borrowing.responses.BorrowingResponse;
import com.maids.library_management_system.borrowing.services.BorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingResponse> borrow(
            @PathVariable("bookId") Integer bookId,
            @PathVariable("patronId") Integer patronId,
            @Valid @ModelAttribute BorrowBookRequest request,
            BindingResult bindingResult
    ) {
        // Manually bind path variables to the request object
        request.setBookId(bookId);
        request.setPatronId(patronId);

        // Validate the request object
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            BorrowingResponse errorResponse = new BorrowingResponse();
            errorResponse.setMessage("Validation failed");
            // Add detailed error messages
            return ResponseEntity.badRequest().body(errorResponse);
        }

        BorrowingResponse response = new BorrowingResponse();
        response.setMessage("Success");

        // perform the operation
        BorrowingRecord record = borrowingService.borrowBook(request);
        response.setData(BorrowingRecordResponse.from(record));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingResponse> returnBook(
            @PathVariable("bookId") Integer bookId,
            @PathVariable("patronId") Integer patronId,
            @Valid @ModelAttribute ReturnBookRequest request,
            BindingResult bindingResult
    ) {
        // Manually bind path variables to the request object
        request.setBookId(bookId);
        request.setPatronId(patronId);

        // Validate the request object
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            BorrowingResponse errorResponse = new BorrowingResponse();
            errorResponse.setMessage("Validation failed");
            // Add detailed error messages
            return ResponseEntity.badRequest().body(errorResponse);
        }

        BorrowingResponse response = new BorrowingResponse();
        response.setMessage("Success");

        // perform the operation
        BorrowingRecord record = borrowingService.returnBook(request);
        response.setData(BorrowingRecordResponse.from(record));
        return ResponseEntity.ok(response);
    }

}
