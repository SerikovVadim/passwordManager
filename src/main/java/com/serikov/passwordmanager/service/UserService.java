package com.serikov.passwordmanager.service;

import com.serikov.passwordmanager.entity.Password;
import com.serikov.passwordmanager.entity.User;
import com.serikov.passwordmanager.repository.PasswordRepository;
import com.serikov.passwordmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public boolean existsById(long id) {
        return !userRepository.existsById(id);
    }

    public List<User> findByAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }
}
