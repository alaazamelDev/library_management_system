package com.maids.library_management_system.patron.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatronRequest {

    private String firstname;

    private String lastname;

    @DateTimeFormat(pattern = "dd-MM-yyy")
    private Date birth_date;

    private String phone_number;
}
