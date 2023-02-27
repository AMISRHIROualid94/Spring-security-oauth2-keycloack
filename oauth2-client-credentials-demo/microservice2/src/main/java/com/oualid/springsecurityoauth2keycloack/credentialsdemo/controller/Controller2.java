package com.oualid.springsecurityoauth2keycloack.credentialsdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/microservice2",produces = "application/json")
@CrossOrigin(value = "*")
public class Controller2 {

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public String helloRestTemplate(){
        return "hello from microservice 2";
    }
}
