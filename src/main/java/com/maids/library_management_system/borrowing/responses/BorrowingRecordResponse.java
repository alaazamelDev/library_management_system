package com.maids.library_management_system.borrowing.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maids.library_management_system.book.responses.BookResponse;
import com.maids.library_management_system.borrowing.entities.BorrowingRecord;
import com.maids.library_management_system.patron.responses.PatronResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecordResponse {

    private Integer id;
    private BookResponse book;
    private PatronResponse patron;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date borrowing_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date returned_date;

    public static BorrowingRecordResponse from(BorrowingRecord record) {
        return BorrowingRecordResponse.builder()
                .id(record.getId())
                .book(BookResponse.from(record.getBook()))
                .patron(PatronResponse.from(record.getPatron()))
                .borrowing_date(record.getCreatedAt())
                .returned_date(record.getReturnDate())
                .build();
    }
}
