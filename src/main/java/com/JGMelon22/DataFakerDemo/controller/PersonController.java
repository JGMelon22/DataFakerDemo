package com.JGMelon22.DataFakerDemo.controller;

import com.JGMelon22.DataFakerDemo.dto.PersonRecordDto;
import com.JGMelon22.DataFakerDemo.model.Person;
import com.JGMelon22.DataFakerDemo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public CompletableFuture<ResponseEntity<List<Person>>> getAllPeople() {
        return personService.listAll()
                .thenApply(people -> people.isEmpty()
                        ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                        : ResponseEntity.status(HttpStatus.OK).body(people));
    }

    @GetMapping("/person/{id}")
    public CompletableFuture<ResponseEntity<Person>> getPersonById(@PathVariable(value = "id") Integer id) {
        return personService.findPersonById(id)
                .thenApply(person -> ResponseEntity.status(HttpStatus.OK).body(person))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/person")
    public CompletableFuture<ResponseEntity<Void>> savePerson(@RequestBody @Valid PersonRecordDto personRecordDto) {
        return personService.save(personRecordDto)
                .thenApply(result -> ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @PostMapping("/person/seed-data")
    public CompletableFuture<ResponseEntity<Void>> seedPersonData() {
        return personService.seedData()
                .thenApply(result -> ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @DeleteMapping("/person")
    public CompletableFuture<ResponseEntity<Void>> deletePerson() {
        return personService.delete()
                .thenApply(result -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}