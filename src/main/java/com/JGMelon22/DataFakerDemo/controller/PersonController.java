package com.JGMelon22.DataFakerDemo.controller;

import com.JGMelon22.DataFakerDemo.dto.PersonRecordDto;
import com.JGMelon22.DataFakerDemo.model.Person;
import com.JGMelon22.DataFakerDemo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.listAll();
        return people.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Integer id) {
        Person person = personService.findPersonById(id);
        return person != null
                ? ResponseEntity.status(HttpStatus.OK).body(person)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/person")
    public ResponseEntity<Person> savePerson(@RequestBody @Valid PersonRecordDto personRecordDto) {
        personService.save(personRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/person/seed-data")
    public ResponseEntity<Person> seedPersonData() {
        personService.seedData();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/person")
    public ResponseEntity<Person> deletePerson() {
        personService.delete();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
