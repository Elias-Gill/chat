package com.elias.chat.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elias.chat.models.User;
import com.elias.chat.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class Authentication {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(400).body("Email already in use");
        }

        if (this.userRepository.existsByUsername(user.getName())) {
            return ResponseEntity.status(400).body("Username already in use");
        }

        user.setPassword(PasswordEncoder.(user.getPassword());
        userRepository.save(user);

        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletRequest request) {
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

            return ResponseEntity.ok(this.generateToken());
        }

        return ResponseEntity.badRequest().build();
    }

    // FIX: generate a real jwt token
    public String generateToken() {
        return "123";
    }
}
