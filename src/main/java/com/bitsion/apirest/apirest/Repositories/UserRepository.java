package com.bitsion.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitsion.apirest.apirest.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
