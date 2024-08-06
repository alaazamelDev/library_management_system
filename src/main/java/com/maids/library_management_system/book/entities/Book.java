package com.maids.library_management_system.book.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity(name = "books")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer publicationYear;

    @Column(length = 100, nullable = false)
    private String isbn;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column()
    private Date updatedAt;
}
