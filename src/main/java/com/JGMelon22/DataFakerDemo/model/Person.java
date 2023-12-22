package com.JGMelon22.DataFakerDemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "people", indexes = @Index(name = "idx_people_people_id", columnList = "person_id"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "first_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String lastName;

    @Column(name = "full_address", columnDefinition = "VARCHAR(255)", nullable = false)
    private String address;
}
