package com.maids.library_management_system.patron.requests;

import com.maids.library_management_system.auth.requests.RegisterRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreatePatronRequest extends RegisterRequest {


    @NotNull(message = "Invalid birth date: birth date is NULL")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date;

    @NotBlank(message = "phone number is required")
    @NotNull(message = "Invalid phone number: phone number is NULL")
    private String phone_number;
}
