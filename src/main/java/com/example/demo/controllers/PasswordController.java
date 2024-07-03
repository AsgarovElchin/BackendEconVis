package com.example.demo.controllers;


import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/password")
public class PasswordController {

    private Map<String, String> resetCodes = new HashMap<>(); // Temporary store, replace with a database or cache in production

    @Autowired
    private UserService userService;

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resetCode = UUID.randomUUID().toString().substring(0, 6); // Simplified code generation
        resetCodes.put(email, resetCode);

        try {
            EmailService.sendPasswordResetEmail(email, resetCode);
            return ResponseEntity.ok("Verification code sent to your email");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String code = data.get("code");

        if (resetCodes.containsKey(email) && resetCodes.get(email).equals(code)) {
            return ResponseEntity.ok("Code verified");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String code = data.get("code");
        String newPassword = data.get("newPassword");

        if (email == null || email.isEmpty() || code == null || code.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email, code, and new password are required");
        }

        if (resetCodes.containsKey(email) && resetCodes.get(email).equals(code)) {
            try {
                userService.updatePassword(email, newPassword); // Example service call
                resetCodes.remove(email); // Remove the used code
                return ResponseEntity.ok("Password reset successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
        }
    }
}