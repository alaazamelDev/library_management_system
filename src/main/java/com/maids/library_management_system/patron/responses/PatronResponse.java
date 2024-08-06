package com.maids.library_management_system.patron.responses;

import com.maids.library_management_system.patron.entities.Patron;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatronResponse {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone_number;
    private String birth_date;

    public static PatronResponse from(Patron patron) {

        return PatronResponse.builder()
                .id(patron.getId())
                .firstname(patron.getUser().getFirstName())
                .lastname(patron.getUser().getLastName())
                .email(patron.getUser().getEmail())
                .phone_number(patron.getPhoneNumber())
                .birth_date(new SimpleDateFormat("dd/MM/yyyy").format(patron.getBirthDate()))
                .build();
    }

    public static List<PatronResponse> from(List<Patron> patrons) {
        return patrons.stream().map(PatronResponse::from).toList();
    }

}
