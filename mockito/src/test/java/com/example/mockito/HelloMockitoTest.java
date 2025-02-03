package com.example.mockito;

import com.example.mockito.util.implementations.PersonRepository;
import com.example.mockito.util.implementations.TranslationService;
import com.example.mockito.util.pojo.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

//Mockito Test Process
//  Create subs to stand in for the dependencies ( methods called from different classes )
//  Setting expectations on the stubs to do what you want
//  Injecting the stubs int the class you are planning to test
//        Testing the methods in the class under test by invoking it methods which in turn call methods on the stubs
//        Checks the methods work as expected
//        Verify that the methods on the dependencies get invoked the number of time in the right order

@ExtendWith(MockitoExtension.class)
public class HelloMockitoTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private HelloMockito helloMockito;

    @Test
    @DisplayName("Verify that \"Hello, Grace, from Mockito!\" is displayed")
    void greetAPersonThatExists() {
        // set the expectations on the mocks
        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9))));
        when(translationService
                .translate("Hello, Grace, from Mockito!", "en", "en"))
                .thenReturn("Hello, Grace, from Mockito!");

        // test the greet method
        String greeting = helloMockito.greet(1, "en", "en");
        assertEquals("Hello, Grace, from Mockito!", greeting);

        // verify the methods are called once, in the right order
        InOrder inOrder = inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService)
                .translate(anyString(), eq("en"), eq("en"));
    }

    @Test
    @DisplayName("Another test to verify that the translation service works correctly")
    void greetAPersonThatDoesNotExist() {
        when(repository.findById(anyInt()))
                .thenReturn(Optional.empty());
        when(translationService
                .translate("Hello, World, from Mockito!", "en", "en"))
                .thenReturn("Hello, World, from Mockito!");

        String greeting = helloMockito.greet(100, "en", "en");
        assertEquals("Hello, World, from Mockito!", greeting);

        // verify the methods are called once, in the right order
        InOrder inOrder = inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService)
                .translate(anyString(), eq("en"), eq("en"));
    }
}
