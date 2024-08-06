package com.maids.library_management_system.patron.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatronDTO {

    private Integer userId;
    private Date birthDate;
    private String phoneNumber;

}
