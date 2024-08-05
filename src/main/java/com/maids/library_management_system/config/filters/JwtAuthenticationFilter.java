package com.maids.library_management_system.config.filters;

import com.maids.library_management_system.config.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // get the auth header
        final String BEARER_KEYWORD = "Bearer ";
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // in case auth header is not passed.
        if (authHeader == null || !authHeader.startsWith(BEARER_KEYWORD)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(BEARER_KEYWORD.length());
        userEmail = jwtService.extractUsername(jwt); // TODO: Extract the user email from JWT Token

    }
}
