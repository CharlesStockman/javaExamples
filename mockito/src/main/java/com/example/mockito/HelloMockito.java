package com.example.mockito;

import com.example.mockito.util.*;
import com.example.mockito.util.implementations.PersonRepository;
import com.example.mockito.util.implementations.TranslationService;
import com.example.mockito.util.pojo.Person;

import java.util.Optional;

public class HelloMockito {

	// Dependencies
	private final PersonRepositoryInterface personRepositoryInterface;
	private final TranslationServiceInterface translationServiceInterface;

	// Constructor to inject the dependencies
	public HelloMockito(PersonRepositoryInterface personRepositoryInterface, TranslationServiceInterface translationServiceInterface) {
		this.personRepositoryInterface = personRepositoryInterface;
		this.translationServiceInterface = translationServiceInterface;
	}

	// Method we want to test
	public String greet(int id, String sourceLang, String targetLang) {
		Optional<Person> person = personRepositoryInterface.findById(id);
		String name = person.map(Person::getFirstName).orElse("World");
		String greeting = "Hello, %s, from Mockito!";
		return translationServiceInterface.translate(String.format(greeting, name), sourceLang, targetLang);
	}

	/**
	 * Example of hwo the function work
	 */
	public static void main(String... args) throws Exception {

		Person person = new Person("Charles" , "Stockman");
		System.out.println("Created a person " + person);

		// Create the Dependencies
		PersonRepositoryInterface personRepository = new PersonRepository();
		TranslationServiceInterface translationService = new TranslationService();

		// Add the person to the repository
		System.out.println("The current number of persons in the data before adding data is " + personRepository.count());
		personRepository.save(person);
		System.out.println("The current number of persons in the data after adding data is " + personRepository.count());

		// Verify that the Person was put into the database
		Optional<Person> personActual = personRepository.findById(person.hashCode());
		if ( personActual.isEmpty()) throw new Exception("Person should have been found, but could not be located");
		if ( personActual.get().hashCode() != person.hashCode() ) throw new Exception("We have treating person as an immutable object, but has a different hashcode");

		// Now that we know we have a person in the database lets use it.
		HelloMockito helloMockito = new HelloMockito(personRepository, translationService);
		String greeting = helloMockito.greet(person.hashCode(), "english", "english");
		System.out.println("Greeting from HelloMockito is " + greeting);

		System.out.println("The current number of persons in the data before adding data is " + personRepository.count());
		personRepository.delete(person);
		System.out.println("The current number of persons in the data after adding data is " + personRepository.count());

	}
}