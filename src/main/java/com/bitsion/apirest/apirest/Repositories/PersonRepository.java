package com.bitsion.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitsion.apirest.apirest.Entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // boolean existsByIdentification(String identification);
}
