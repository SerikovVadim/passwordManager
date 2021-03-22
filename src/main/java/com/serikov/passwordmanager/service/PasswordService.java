package com.serikov.passwordmanager.service;

import com.serikov.passwordmanager.entity.Password;
import com.serikov.passwordmanager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public Optional<Password> findById(long id) {
        return passwordRepository.findById(id);
    }

    public boolean existsById(long id) {
        return !passwordRepository.existsById(id);
    }

    public List<Password> findByAll() {
        return passwordRepository.findAll();
    }

    public void save(Password film) {
        passwordRepository.save(film);
    }

    public void delete(long id) {
        passwordRepository.deleteById(id);
    }
}
