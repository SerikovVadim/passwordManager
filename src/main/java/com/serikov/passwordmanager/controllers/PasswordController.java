package com.serikov.passwordmanager.controllers;

import com.serikov.passwordmanager.entity.Password;
import com.serikov.passwordmanager.service.PasswordService;
import com.serikov.passwordmanager.util.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/passwords")
public class PasswordController {

    private final PasswordService passwordService;
    private final AuthenticationUser authenticationUser;

    @Autowired
    public PasswordController(PasswordService passwordService, AuthenticationUser authenticationUser) {
        this.passwordService = passwordService;
        this.authenticationUser = authenticationUser;
    }

    @GetMapping
    public String index(Model model) {
        List<Password> passwordList = authenticationUser.getUser().getPasswordList();
        model.addAttribute("passwordList", passwordList);
        model.addAttribute("pageTitle", "Менеджер паролей");
        return "password/show";

    }

    @GetMapping("/add")
    public String addPasswordManagerForm(@ModelAttribute("password") Password password,
                                         Model model) {
        model.addAttribute("pageTitle", "Добавление пароля в базу");
        return "password/add";
    }

    @PostMapping("/add")
    public String addPasswordManager(@ModelAttribute("password") @Valid Password password,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/password/add";
        }
        password.setUser(authenticationUser.getUser());
        passwordService.save(password);
        return "redirect:/passwords";
    }

    @GetMapping("{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        if (passwordService.existsById(id)) {
            return "redirect:/passwords";
        }
        Optional<Password> password = passwordService.findById(id);
        List<Password> passwordList = new ArrayList<>();
        password.ifPresent(passwordList::add);
        model.addAttribute("passwordList", passwordList);
        model.addAttribute("pageTitle", "Подробнее");
        return "password/details";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") long id, Model model) {
        if (passwordService.existsById(id)) {
            return "redirect:/passwords";
        }
        Password password = passwordService.findById(id).orElseThrow(AssertionError::new);
        model.addAttribute("password", password);
        model.addAttribute("pageTitle", "Редактировать пароль");
        return "password/edit";
    }

    @PatchMapping("{id}")
    public String edit(@ModelAttribute("password") @Valid Password password,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "password/edit";
        }
        password.setUser(authenticationUser.getUser());
        passwordService.save(password);
        return "redirect:/passwords";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        passwordService.delete(id);
        return "redirect:/passwords";
    }

}
