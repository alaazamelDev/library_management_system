package com.maids.library_management_system.borrowing.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingResponse {

    private String message;
    private BorrowingRecordResponse data;
}
