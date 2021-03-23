package com.serikov.passwordmanager.controllers;

import com.serikov.passwordmanager.entity.Role;
import com.serikov.passwordmanager.entity.Status;
import com.serikov.passwordmanager.entity.User;
import com.serikov.passwordmanager.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String createUserForm(@ModelAttribute("user") User user,
                                 Model model) {
        model.addAttribute("pageTitle", "Регистрация пользователя");
        return "user/add";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/add";
        }
        user.setPassword(new BCryptPasswordEncoder(12)
                .encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userService.save(user);
        return "redirect:/";
    }
}
