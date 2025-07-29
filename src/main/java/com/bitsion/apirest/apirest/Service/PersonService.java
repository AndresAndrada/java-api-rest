package com.bitsion.apirest.apirest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsion.apirest.apirest.Entities.Person;
import com.bitsion.apirest.apirest.Repositories.PersonRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;
    private final Validator validator;

    public PersonService(PersonRepository personRepository, Validator validator) {
        this.personRepository = personRepository;
        this.validator = validator;
    }

    public Person createPerson(Person person) {

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            System.out.println(violations.iterator().next().getMessage());
            throw new IllegalArgumentException(violations.iterator().next().getMessage());
        }
   
        if (personRepository.existsByDocumentation(person.getDocumentation())) {
            throw new IllegalArgumentException("La identificación ya está registrada");
        }

        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));

        Set<ConstraintViolation<Person>> violations = validator.validate(updatedPerson);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException(violations.iterator().next().getMessage());
        }

        if (!existingPerson.getDocumentation().equals(updatedPerson.getDocumentation()) &&
                personRepository.existsByDocumentation(updatedPerson.getDocumentation())) {
            throw new IllegalArgumentException("La identificación ya está registrada");
        }

        existingPerson.setFullName(updatedPerson.getFullName());
        existingPerson.setDocumentation(updatedPerson.getDocumentation());
        existingPerson.setAge(updatedPerson.getAge());
        existingPerson.setGender(updatedPerson.getGender());
        existingPerson.setIsActive(updatedPerson.getIsActive());
        existingPerson.setDrives(updatedPerson.getDrives());
        existingPerson.setWearsGlasses(updatedPerson.getWearsGlasses());
        existingPerson.setIsDiabetic(updatedPerson.getIsDiabetic());
        existingPerson.setOtherDisease(updatedPerson.getOtherDisease());

        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new IllegalArgumentException("Persona no encontrada");
        }
        personRepository.deleteById(id);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}