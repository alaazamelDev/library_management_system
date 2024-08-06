package com.maids.library_management_system.borrowing.entities;

import com.maids.library_management_system.book.entities.Book;
import com.maids.library_management_system.patron.entities.Patron;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "borrowing_records")
public class BorrowingRecord {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(targetEntity = Patron.class, optional = false)
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    @ManyToOne(targetEntity = Book.class, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column()
    private Date returnDate;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column()
    private Date updatedAt;
}
