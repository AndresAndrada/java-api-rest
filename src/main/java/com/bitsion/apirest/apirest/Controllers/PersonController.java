package com.bitsion.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsion.apirest.apirest.Entities.Person;
import com.bitsion.apirest.apirest.Repositories.PersonRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getMethodName() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro la persona con el id: " + id));

    }
    
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public Person putMethodName(@PathVariable Long id, @RequestBody Person deatilPerson) {
        Person person = personRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro la persona con el id: " + id));
        person.setAge(deatilPerson.getAge());
        person.setFullName(deatilPerson.getFullName());

        return personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro la persona con el id: " + id));

        personRepository.delete(person);
        return "La persona con el id: " + id + " fue eliminado";
    }
}
