package com.example.spring6RestMvc.exception;

import com.example.spring6RestMvc.model.Beer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//When uncommented then for any NotFoundException thrown in the Controller/Service/Data Layer the handleNotFound
//Exception will be thrown
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        System.out.println("*** Activated Exception Handler *****");
        return ResponseEntity.notFound().build();
    }

}
