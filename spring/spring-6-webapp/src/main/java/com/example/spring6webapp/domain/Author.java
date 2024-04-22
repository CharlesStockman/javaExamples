package com.example.spring6webapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    // "authors" is the property in Book that this declaration refers to
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();


}
