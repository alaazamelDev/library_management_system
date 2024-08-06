package com.maids.library_management_system.auth.services;

import com.maids.library_management_system.auth.entities.User;
import com.maids.library_management_system.auth.repositories.UserRepository;
import com.maids.library_management_system.auth.requests.AuthenticationRequest;
import com.maids.library_management_system.auth.requests.RegisterRequest;
import com.maids.library_management_system.auth.responses.AuthenticationResponse;
import com.maids.library_management_system.config.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        // create a user entity
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        final User user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        // save it
        userRepository.save(user);

        // generate an access token
        var accessToken = jwtService.generateAccessToken(user);
        System.out.println(accessToken);
        return AuthenticationResponse.builder()
                .access_token(accessToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // authenticate the user against the given credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("username or password is wrong"));

        var token = jwtService.generateAccessToken(user);
        return AuthenticationResponse.builder()
                .access_token(token)
                .build();
    }
}
