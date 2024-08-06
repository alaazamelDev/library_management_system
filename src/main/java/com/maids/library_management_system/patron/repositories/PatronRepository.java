package com.maids.library_management_system.patron.repositories;

import com.maids.library_management_system.patron.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Integer> {
}
