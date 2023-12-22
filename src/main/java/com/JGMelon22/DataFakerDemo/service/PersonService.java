package com.JGMelon22.DataFakerDemo.service;

import com.JGMelon22.DataFakerDemo.dto.PersonRecordDto;
import com.JGMelon22.DataFakerDemo.model.Person;
import com.JGMelon22.DataFakerDemo.repository.PersonRepository;
import net.datafaker.Faker;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> listAll() {
        return personRepository.findAll().stream().limit(100).toList();
    }

    public void save(PersonRecordDto personRecordDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personRecordDto, person);

        personRepository.save(person);
    }

    public void delete() {
        personRepository.deleteAll();
    }

    public void seedData() {
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) { // Generate 10 fake Person records as an example
            Person person = new Person();
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setAddress(faker.address().fullAddress());

            personRepository.save(person); // Save the fake Person record to the database
        }
    }
}
