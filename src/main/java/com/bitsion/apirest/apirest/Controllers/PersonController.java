package com.bitsion.apirest.apirest.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bitsion.apirest.apirest.Entities.Person;
import com.bitsion.apirest.apirest.Service.PersonService;
import com.bitsion.apirest.apirest.dto.PersonDTO;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        try {
            Person person = convertToEntity(personDTO);
            Person createdPerson = personService.createPerson(person);
            return ResponseEntity.ok(convertToDTO(createdPerson));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        try {
            Person person = convertToEntity(personDTO);
            Person updatedPerson = personService.updatePerson(id, person);
            return ResponseEntity.ok(convertToDTO(updatedPerson));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        try {
            Person person = personService.getPersonById(id);
            return ResponseEntity.ok(convertToDTO(person));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        List<PersonDTO> personDTOs = persons.stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(personDTOs);
    }

    private PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFullName(person.getFullName());
        dto.setDocumentation(person.getDocumentation());
        dto.setAge(person.getAge());
        dto.setGender(person.getGender());
        dto.setIsActive(person.getIsActive());
        dto.setDrives(person.getDrives());
        dto.setWearsGlasses(person.getWearsGlasses());
        dto.setIsDiabetic(person.getIsDiabetic());
        dto.setOtherDisease(person.getOtherDisease());
        return dto;
    }

    private Person convertToEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setFullName(dto.getFullName());
        person.setDocumentation(dto.getDocumentation());
        person.setAge(dto.getAge());
        person.setGender(dto.getGender());
        person.setIsActive(dto.getIsActive());
        person.setDrives(dto.getDrives());
        person.setWearsGlasses(dto.getWearsGlasses());
        person.setIsDiabetic(dto.getIsDiabetic());
        person.setOtherDisease(dto.getOtherDisease());
        return person;
    }
}