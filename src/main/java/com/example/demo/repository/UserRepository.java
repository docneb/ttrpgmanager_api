package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA bu isimden otomatik sorgu üretir:
    // SELECT * FROM users WHERE email = ?
    User findByEmail(String email);
}