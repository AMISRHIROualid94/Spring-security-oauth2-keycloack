package com.oualid.springsecurityoauth2keycloack.credentialsdemo.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping(value = "/microservice1",produces = "application/json")
public class Controller1 {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final WebClient webClient = WebClient.builder().build();

    @GetMapping("/home/rest-template")
    @ResponseStatus(HttpStatus.OK)
    public String helloRestTemplate() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt.getTokenValue());
        //httpHeaders.add("Authorization", "Bearer " + jwt.getTokenValue());
        ResponseEntity<String> exchange = restTemplate
                .exchange("http://localhost:8084/microservice2/home",
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders),
                        String.class);
        return "hello - message from microservice 2 -  " + exchange.getBody();
    }







    @GetMapping("/home/webclient")
    @ResponseStatus(HttpStatus.OK)
    public String helloWebClient() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String response = webClient.get()
                .uri("http://localhost:8084/microservice2/home")
                .headers(header ->header.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "hello - message from microservice 2 -  " + response;
    }
}
