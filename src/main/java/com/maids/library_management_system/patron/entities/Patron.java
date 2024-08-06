package com.maids.library_management_system.patron.entities;

import com.maids.library_management_system.auth.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patrons")
public class Patron {

    @Id
    @GeneratedValue
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private User user;

    @Column(nullable = false)
    private Date birthDate;

    @Column(length = 20)
    private String phoneNumber;
}
