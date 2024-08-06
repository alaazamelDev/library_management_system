package com.maids.library_management_system.patron.services;

import com.maids.library_management_system.auth.entities.User;
import com.maids.library_management_system.auth.repositories.UserRepository;
import com.maids.library_management_system.auth.requests.RegisterRequest;
import com.maids.library_management_system.auth.services.AuthService;
import com.maids.library_management_system.config.exceptions.ResourceNotFoundException;
import com.maids.library_management_system.patron.entities.Patron;
import com.maids.library_management_system.patron.repositories.PatronRepository;
import com.maids.library_management_system.patron.requests.CreatePatronRequest;
import com.maids.library_management_system.patron.requests.UpdatePatronRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository patronRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<Patron> findAll() {
        return patronRepository.findAll();
    }

    public Patron findOne(Integer id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    @Transactional
    public Patron save(CreatePatronRequest request) {

        // first, create a user
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        var authenticationResponse = authService.register(registerRequest);

        var token = authenticationResponse.getAccess_token();
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Error occurred while creating patron");
        }

        // Fetch the created user by email (given that the email is unique)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User creation failed"));

        // Now, create the patron
        Patron patron = Patron.builder()
                .user(user)
                .birthDate(request.getBirth_date())
                .phoneNumber(request.getPhone_number())
                .build();

        // Save and return the patron
        return patronRepository.save(patron);
    }


    @Transactional
    public Patron update(Integer id, UpdatePatronRequest request) {

        // check if the patron is existing.
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));

        // Update the fields if they are provided in the request
        if (request.getFirstname() != null) {
            existingPatron.getUser().setFirstName(request.getFirstname());
        }

        if (request.getLastname() != null) {
            existingPatron.getUser().setLastName(request.getLastname());
        }

        if (request.getBirth_date() != null) {
            existingPatron.setBirthDate(request.getBirth_date());
        }

        if (request.getPhone_number() != null) {
            existingPatron.setPhoneNumber(request.getPhone_number());
        }

        return patronRepository.save(existingPatron);
    }

    public void delete(Integer id) {
        if (!patronRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patron not found with id: " + id);
        }

        patronRepository.deleteById(id);
    }

}
