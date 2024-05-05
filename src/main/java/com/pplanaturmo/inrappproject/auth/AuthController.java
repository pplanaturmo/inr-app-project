package com.pplanaturmo.inrappproject.auth;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "login")
    public String login(@RequestBody String entity) {
        // TODO: process POST request

        return "<h1>1</h1>";
    }

    @PostMapping(value = "register")
    public String register(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

}
