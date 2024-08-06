package com.maids.library_management_system.patron.controllers;

import com.maids.library_management_system.patron.entities.Patron;
import com.maids.library_management_system.patron.requests.CreatePatronRequest;
import com.maids.library_management_system.patron.requests.UpdatePatronRequest;
import com.maids.library_management_system.patron.responses.PatronResponse;
import com.maids.library_management_system.patron.services.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;


    @GetMapping
    public ResponseEntity<List<PatronResponse>> getAll() {
        List<Patron> patronsList = patronService.findAll();
        List<PatronResponse> result = PatronResponse.from(patronsList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronResponse> getById(@PathVariable("id") Integer id) {
        PatronResponse result = PatronResponse.from(patronService.findOne(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PatronResponse> create(@Valid @RequestBody CreatePatronRequest request) {
        PatronResponse result = PatronResponse.from(patronService.save(request));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronResponse> update(
            @PathVariable("id") Integer id,
            @Valid @RequestBody UpdatePatronRequest request
    ) {
        PatronResponse result = PatronResponse.from(patronService.update(id, request));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        patronService.delete(id);
    }

}
