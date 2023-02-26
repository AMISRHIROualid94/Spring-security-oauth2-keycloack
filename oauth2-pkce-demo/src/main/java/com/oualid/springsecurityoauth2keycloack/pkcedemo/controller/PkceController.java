package com.oualid.springsecurityoauth2keycloack.pkcedemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/home",produces = "application/json")
@CrossOrigin(origins = "*")
public class PkceController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String home(){
        return "Welcome home";
    }
}
