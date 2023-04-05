package com.patagonia.web.controller;

import com.patagonia.web.entity.*;
import com.patagonia.web.entity.ResponseWrapper;
import com.patagonia.web.entity.User;
import com.patagonia.web.entity.UserRequest;
import com.patagonia.web.entity.UserResponse;
import com.patagonia.web.filter.search.Filters;
import com.patagonia.web.repository.UserRepository;
import com.patagonia.web.service.AuthenticateService;
import com.patagonia.web.service.FilterService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/secured")
public class AdminController {

    private final AuthenticateService authenticateService;
    private final UserRepository userRepository;
    private final FilterService filterService;

    public AdminController(AuthenticateService authenticateService, UserRepository userRepository, FilterService filterService) {
        this.authenticateService = authenticateService;
        this.userRepository = userRepository;
        this.filterService = filterService;
    }

    @PostMapping("/createUser")
    public ResponseWrapper<T> createUser(@RequestBody UserRequest request,
                                         @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        return authenticateService.createUser(request, token);
    }

    @PostMapping("/getAllUsers")
    public ResponseWrapper<?> getAllUsers(@RequestBody(required = false) Filters filters) {
        return filterService.getAllUsers(filters, User.class, userRepository);
    }

    @PostMapping("/getUserById")
    public ResponseWrapper<UserResponse> getUserById(@RequestBody(required = false) Map<String, Long> request) {
        return filterService.getUserById(request.get("id"));
    }
    @PostMapping("/editUser")
    public ResponseWrapper<T> editUser(@RequestBody UserRequest request,
                                       @RequestHeader("Authorization") String tokenHeader){
        String token = tokenHeader.replace("Bearer ", "");
        return authenticateService.editUser(request, token);
    }

}
