package com.patagonia.web.controller;

import com.patagonia.web.entity.AuthenticationRequest;
import com.patagonia.web.entity.AuthenticationResponse;
import com.patagonia.web.entity.ResponseWrapper;
import com.patagonia.web.entity.UserRequest;
import com.patagonia.web.service.AuthenticateService;
import com.patagonia.web.service.JwtService;
import com.patagonia.web.util.LogUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LogUtil logUtil;
    private final AuthenticateService authenticateService;
    private final JwtService jwtService;

    public AuthenticationController(LogUtil logUtil, AuthenticateService authenticateService, JwtService jwtService) {
        this.logUtil = logUtil;
        this.authenticateService = authenticateService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseWrapper<AuthenticationResponse> register(@RequestBody UserRequest request) {
        AuthenticationResponse response = authenticateService.register(request);
        return new ResponseWrapper<>(true, "User created");
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



