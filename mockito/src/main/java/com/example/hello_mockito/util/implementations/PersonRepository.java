package com.example.hello_mockito.util.implementations;

import com.example.hello_mockito.util.PersonRepositoryInterface;
import com.example.hello_mockito.util.pojo.Person;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
public class PersonRepository implements PersonRepositoryInterface {

    private HashMap<Integer, Person> persons;

    public PersonRepository() {
        persons = new HashMap<>();
    }

    @Override
    public Person save(Person person) {
        if ( person == null )
            throw new NullPointerException("The person instance cannot be null");
        else
            persons.put(person.hashCode(), person);

        return person;
    }

    @Override
    public Optional<Person> findById(int id) {
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) persons.values();
    }

    @Override
    public long count() {
        return persons.values().size();
    }

    @Override
    public void delete(Person person) {
        persons.remove(person.hashCode());

    }
}
