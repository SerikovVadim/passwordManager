package com.serikov.passwordmanager.repository;

import com.serikov.passwordmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *  Получить пользователя из базы данных по email.
     */
    Optional<User> findByEmail(String email);
}
