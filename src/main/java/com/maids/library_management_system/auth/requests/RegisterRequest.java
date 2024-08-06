package com.maids.library_management_system.auth.requests;

import com.maids.library_management_system.auth.constraints.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "firstname is required")
    @NotNull(message = "Invalid firstname: firstname is NULL")
    private String firstname;

    @NotBlank(message = "lastname is required")
    @NotNull(message = "Invalid lastname: lastname is NULL")
    private String lastname;

    @NotBlank(message = "email is required")
    @NotNull(message = "Invalid email: email is NULL")
    @Email(message = "email is not valid")
    @UniqueEmail
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should have a minimum length of 8")
    private String password;
}
