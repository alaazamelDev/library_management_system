package com.maids.library_management_system.borrowing.repositories;

import com.maids.library_management_system.borrowing.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

    @Query("SELECT br FROM borrowing_records br WHERE br.book.id = :bookId AND br.patron.id = :patronId AND br.returnDate IS NULL")
    Optional<BorrowingRecord> findActiveBorrowingRecord(
            @Param("bookId") Integer bookId,
            @Param("patronId") Integer patronId
    );

}
