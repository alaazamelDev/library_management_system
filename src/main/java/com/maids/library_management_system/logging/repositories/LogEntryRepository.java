package com.maids.library_management_system.logging.repositories;

import com.maids.library_management_system.logging.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
