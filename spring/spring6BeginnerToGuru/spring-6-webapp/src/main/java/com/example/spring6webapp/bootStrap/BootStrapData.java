package com.example.spring6webapp.bootStrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,  PublisherRepository publisherRepository1) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository1;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("12345");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved  = bookRepository.save(ddd);

        // Second Book
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("23456");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        // Publisher
        Publisher publisher = new Publisher();
        publisher.setAddress("123 Test Suite 300");
        publisher.setPublisherName("xyz corp");
        publisher.setCity("Raleigh");
        publisher.setZip("27809");

        Publisher firstCompanySaved = publisherRepository.save(publisher);
        dddSaved.setPublisher(firstCompanySaved);
        noEJBSaved.setPublisher(firstCompanySaved);

        System.out.println("In Books");
        System.out.println("Author Count = " + authorRepository.count());
        System.out.println("Book Count " + bookRepository.count());
        System.out.println("Publisher Count " + publisherRepository.count());

    }
}
