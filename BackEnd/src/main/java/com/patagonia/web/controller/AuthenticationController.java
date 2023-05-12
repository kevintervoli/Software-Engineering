package com.patagonia.web.controller;

import com.patagonia.web.entity.*;
import com.patagonia.web.repository.UserRepository;
import com.patagonia.web.service.AuthenticateService;
import com.patagonia.web.service.JwtService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticateService authenticateService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticateService authenticateService, JwtService jwtService, UserRepository userRepository) {
        this.authenticateService = authenticateService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/registerUser")
    public ResponseWrapper<AuthenticationResponse> registerUser(@RequestBody UserRequest request) {
        AuthenticationResponse response = authenticateService.registerUser(request);
        return new ResponseWrapper<>(true, "User created");
    }

    @PostMapping("/registerAdmin")
    public ResponseWrapper<AuthenticationResponse> registerAdmin(@RequestBody AdminRequest request) {
        AuthenticationResponse response = authenticateService.registerAdmin(request);
        return new ResponseWrapper<>(true, "Admin created");
    }

    @PostMapping("/authenticate")
    public ResponseWrapper<T> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticateService.authenticate(request);
    }

    @RequestMapping(value = "/refresh")
    public ResponseWrapper<AuthenticationResponse> refresh(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        return jwtService.decodeToken(token);
    }

}



