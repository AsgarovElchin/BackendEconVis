package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


    public ResponseEntity<?> signIn(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null && passwordEncoder.matches(password, existingUser.getPassword())) {
            return ResponseEntity.ok("User signed in successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    public ResponseEntity<?> checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
    }

    public void updatePassword(String email, String newPassword) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }
}


