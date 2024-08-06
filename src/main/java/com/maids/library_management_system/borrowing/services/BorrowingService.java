package com.maids.library_management_system.borrowing.services;

import com.maids.library_management_system.book.entities.Book;
import com.maids.library_management_system.book.repositories.BookRepository;
import com.maids.library_management_system.borrowing.entities.BorrowingRecord;
import com.maids.library_management_system.borrowing.repositories.BorrowingRecordRepository;
import com.maids.library_management_system.borrowing.requests.BorrowBookRequest;
import com.maids.library_management_system.borrowing.requests.ReturnBookRequest;
import com.maids.library_management_system.config.exceptions.ResourceNotFoundException;
import com.maids.library_management_system.patron.entities.Patron;
import com.maids.library_management_system.patron.repositories.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;

    public BorrowingRecord borrowBook(BorrowBookRequest dto) {

        // get the book by id
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // get the patron by id
        Patron patron = patronRepository.findById(dto.getPatronId())
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .build();

        return borrowingRecordRepository.save(borrowingRecord);
    }


    public BorrowingRecord returnBook(ReturnBookRequest dto) {

        // check if the patron is already borrowing the book or not
        BorrowingRecord borrowingRecord = borrowingRecordRepository
                .findActiveBorrowingRecord(dto.getBookId(), dto.getPatronId())
                .orElseThrow(() -> new ResourceNotFoundException("No matching borrowing operation was found!"));

        // update the record
        borrowingRecord.setReturnDate(new Date());

        return borrowingRecordRepository.save(borrowingRecord);
    }

}
