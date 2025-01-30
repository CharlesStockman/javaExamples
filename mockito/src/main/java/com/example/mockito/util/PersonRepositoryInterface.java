package com.example.mockito.util;

import com.example.mockito.util.pojo.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryInterface {

				Person save(Person person);

         	    Optional<Person> findById(int id);

         	    List<Person> findAll();

         	    long count();

         	    void delete(Person person);
}
