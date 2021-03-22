package com.serikov.passwordmanager.repository;

import com.serikov.passwordmanager.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
