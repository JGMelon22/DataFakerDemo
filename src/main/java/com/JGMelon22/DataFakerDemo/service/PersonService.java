package com.JGMelon22.DataFakerDemo.service;

import com.JGMelon22.DataFakerDemo.dto.PersonRecordDto;
import com.JGMelon22.DataFakerDemo.model.Person;
import com.JGMelon22.DataFakerDemo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import net.datafaker.Faker;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> listAll() {
        return personRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Person::getId).reversed())
                .limit(100)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "person")
    public Person findPersonById(Integer id) {
        Optional<Person> person0 = personRepository.findById(id);
        if (person0.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Person with id %o not found!", id));
        return person0.get();
    }

    @CacheEvict(value = "person", allEntries = true)
    public void save(PersonRecordDto personRecordDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personRecordDto, person);

        personRepository.save(person);
    }

    public Person updatePerson(Integer id, PersonRecordDto personRecordDto) {
        Optional<Person> person0 = personRepository.findById(id);
        if (person0.isPresent()) {
            Person person = person0.get();
            BeanUtils.copyProperties(personRecordDto, person);
            return personRepository.save(person);
        }
        throw new EntityNotFoundException("Person with id\"" + id + "\" not found!");
    }

    public void delete() {
        personRepository.deleteAll();
    }

    public void seedData() {
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setAddress(faker.address().fullAddress());

            people.add(person);
        }

        personRepository.saveAll(people);
    }
}
