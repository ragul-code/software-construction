package com.myapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory user store shared across servlets.
 * Key   = email  (what the user types as "username" at login)
 * Value = password (plain text for now — use BCrypt in production)
 */
public class UserStore {

    // Shared map across the whole application
    private static final Map<String, String> users = new HashMap<>();

    /** Save a new user */
    public static void register(String email, String password) {
        users.put(email.trim().toLowerCase(), password);
    }

    /** Return true if email + password match a registered user */
    public static boolean isValid(String email, String password) {
        String stored = users.get(email.trim().toLowerCase());
        return stored != null && stored.equals(password);
    }

    /** Check if an email is already taken */
    public static boolean exists(String email) {
        return users.containsKey(email.trim().toLowerCase());
    }
}
