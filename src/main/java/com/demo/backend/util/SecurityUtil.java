package com.demo.backend.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

public class SecurityUtil {
    private SecurityUtil() {

    }

    @SuppressWarnings("unchecked")
    public static Optional<String> getCurrentUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }
        Authentication authentication = context.getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return Optional.empty();
        }
        return (Optional<String>) principal;
    }
}
