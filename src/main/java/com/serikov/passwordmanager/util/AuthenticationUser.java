package com.serikov.passwordmanager.util;

import com.serikov.passwordmanager.entity.User;
import com.serikov.passwordmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationUser {
    private final UserService userService;

    @Autowired
    public AuthenticationUser(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        String email = "";
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (userDetail != null) {
                email = userDetail.getUsername();
            }
        }
        Optional<User> optionalUser = userService.getUser(email);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }
}
