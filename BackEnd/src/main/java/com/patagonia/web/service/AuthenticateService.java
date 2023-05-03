package com.patagonia.web.service;

import com.patagonia.web.entity.*;
import com.patagonia.web.entity.*;
import com.patagonia.web.repository.RoleRepository;
import com.patagonia.web.repository.UserRepository;
import com.patagonia.web.util.LogUtil;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.formula.functions.T;


import java.util.*;

@Service
public class AuthenticateService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LogUtil logUtil;

    public AuthenticateService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, LogUtil logUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.logUtil = logUtil;
    }


    public AuthenticationResponse register(UserRequest request) {

        boolean userExists = userRepository.findByUsername(request.getUsername())
                .isPresent();

        if (userExists) {
            logUtil.warn("username already taken");
            throw new IllegalStateException("username already taken");
        }

        var user = new User(
                request.getName(),
                request.getAge(),
                request.getEmail(),
                request.getAddress(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getStatus(),
                request.getCreditScore(),
                request.isEnabled(),
                roleRepository.findById(request.getRoleId()).orElseThrow(() -> new UsernameNotFoundException("Role not found!"))
        );

        userRepository.save(user);


        logUtil.info("User with username: " + user.getUsername() + " has been registered successfully");
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole());

    }

    public ResponseWrapper<T> createUser(UserRequest request, String token) {

        AuthenticationResponse auth = (AuthenticationResponse) jwtService.decodeToken(token).getContent().get(0);

        if(2 == auth.getRole().getId() && request.getRoleId() == 1){
            return new ResponseWrapper<>(false, "You dont have authorization to make changes!");
        }else {
            boolean userExists = userRepository.findByUsername(request.getUsername())
                    .isPresent();

            if (userExists) {
                logUtil.warn("username already taken");
                throw new IllegalStateException("username already taken");
            }

            var user = new User(
                    request.getName(),
                    request.getAge(),
                    request.getEmail(),
                    request.getAddress(),
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getStatus(),
                    request.getCreditScore(),
                    request.isEnabled(),
                    roleRepository.findById(request.getRoleId()).orElseThrow(() -> new UsernameNotFoundException("Role not found!"))
            );

            userRepository.save(user);

            logUtil.info("User with username: " + user.getUsername() + " has been registered successfully");
            var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
            List<AuthenticationResponse> content = new ArrayList<>();
            content.add(new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole()));
            return new ResponseWrapper<>(true, content);
        }
    }

    public ResponseWrapper<List<UserResponse>>getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();

        /*

        this.id = id;
        this.username = username;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.status = status;
        this.creditScore = creditScore;
        this.enabled = enabled;
        this.role = role;
         */

        for (User user : users) {
            response.add(new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getStatus(),
                    user.getCreditScore(),
                    user.isEnabled(),
                    user.getRole()
            ));
        }

        ResponseWrapper<List<UserResponse>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setContent(response);

        return responseWrapper;
    }

    public ResponseWrapper<T> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
        } catch (AuthenticationException e) {
            System.err.println("e :" + e.getMessage());
            return new ResponseWrapper<>(false, "Invalid username or password");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + request.getUsername()));

        if (!user.isEnabled()) {
            return new ResponseWrapper<>(false, "User is not enabled");
        }
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole());
        List<AuthenticationResponse> content = new ArrayList<>();
        content.add(authenticationResponse);
        return new ResponseWrapper<>(true, "User authenticated successfully", content);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getAuthorities(user.getRole().getName()));
    }

    public ResponseWrapper<T> editUser(UserRequest request, String token) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        AuthenticationResponse auth = (AuthenticationResponse) jwtService.decodeToken(token).getContent().get(0);


        if (auth.getRole().getId() == 2 && user.getRole().getId() == 1){
            return new ResponseWrapper<>(false, "You dont have authorization to make changes!");
        }else {
            updateUser(user, request);

            userRepository.save(user);
            String successMessage = "User with username: " + user.getUsername() + " has been edited successfully";
            logUtil.info(successMessage);
            return new ResponseWrapper<>(true, successMessage);
        }
    }
    private void updateUser(User user, UserRequest request) {

        Optional.ofNullable(request.getName()).ifPresent(user::setName);
        Optional.ofNullable(request.getAge()).ifPresent(user::setAge);
        Optional.ofNullable(request.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(request.getAddress()).ifPresent(user::setAddress);
        Optional.ofNullable(request.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(request.getStatus()).ifPresent(user::setStatus);
        Optional.ofNullable(request.getCreditScore()).ifPresent(user::setCreditScore);

        if (request.getRoleId() != null) {
            Optional.of(request.getRoleId())
                    .map(roleRepository::findById)
                    .orElseThrow(() -> new UsernameNotFoundException("Role not found!"))
                    .ifPresent(user::setRole);
        }

        Optional.of(request.isEnabled()).ifPresent(user::setEnabled);
    }

    public boolean changePassword(String token, String oldPassword, String newPassword) {

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));

        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public ResponseWrapper<List<Role>> getRoles() {

        List<Role> roles = roleRepository.findAll();
        return new ResponseWrapper<>(true, "The list with roles is retrived.", roles, roles.size());
    }

}
