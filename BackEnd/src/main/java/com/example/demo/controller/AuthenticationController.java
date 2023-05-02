package com.example.demo.controller;

import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.ResponseWrapper;
import com.example.demo.entity.UserRequest;
import com.example.demo.service.AuthenticateService;
import com.example.demo.service.JwtService;
import com.example.demo.util.LogUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LogUtil logUtil;
    private final AuthenticateService service;
    private final JwtService jwtService;

    public AuthenticationController(LogUtil logUtil, AuthenticateService service, JwtService jwtService) {
        this.logUtil = logUtil;
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping("/createUser")
    public ResponseWrapper<AuthenticationResponse> createUser(@RequestBody UserRequest request) {
        AuthenticationResponse response = service.register(request);
        return new ResponseWrapper<>(true, "User created");
    }

    @PostMapping("/authenticate")
    public ResponseWrapper<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.authenticate(request);
        return new ResponseWrapper<>(true, "User authenticated", response);
    }

    @RequestMapping(value = "/refresh")
    public ResponseWrapper<AuthenticationResponse> refresh(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        AuthenticationResponse decodedToken = jwtService.decodeToken(token);
        return new ResponseWrapper<>(true, "Token refreshed", decodedToken);
    }

}



