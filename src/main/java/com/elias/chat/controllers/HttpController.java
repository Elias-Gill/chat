package com.elias.chat.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elias.chat.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class HttpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String mainPage() {
        return "index.html";
    }

    @GetMapping("/login")
    public ResponseEntity<Token> getRequestInfo(HttpServletRequest request) {
        // Get the Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Decode the Base64-encoded credentials
            String base64Credentials = authHeader.substring("Basic ".length());
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String decodedCredentials = new String(decodedBytes);

            // Split the credentials into username and password
            String[] parts = decodedCredentials.split(":", 2);
            String username = parts[0];
            String password = parts[1];
            // TODO: compare password and generate token

            return ResponseEntity.ok(new Token());
        }

        return ResponseEntity.badRequest().build();
    }

    // TODO: properly generate the jwt token
    private class Token {
        @SuppressWarnings("unused")
        public String jwt;

        public Token() {
            // FIX: generate a real jwt token
            this.jwt = "123";
        }
    }
}
