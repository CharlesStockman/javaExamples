package com.example.spring6RestMvc.controller;

import com.example.springAop.controller.Controller;
import com.example.springAop.service.serviceImplementaitons.MyService;
import org.junit.jupiter.api.Test;

public class ControllerTest {

    @Test
    public void show() {

        MyService service = new MyService();
        Controller control = new Controller(service);
        control.getWorkday();
    }
}
