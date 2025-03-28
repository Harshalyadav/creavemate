package com.cravemate.utils;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash password
    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    // Verify password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
    	
    
        return encoder.matches(plainPassword, hashedPassword);
    }
}
