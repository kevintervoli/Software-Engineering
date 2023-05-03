package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.LogUtil;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                roleRepository.findById(request.getRoleId()).orElseThrow(() -> new UsernameNotFoundException("Role not found!")),
                request.isEnabled());

        userRepository.save(user);

        logUtil.info("User with username: " + user.getUsername() + " has been registered successfully");
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole());

    }

    public ResponseWrapper<List<UserResponse>>getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();

        for (User user : users) {
            response.add(new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getRole(),
                    user.isEnabled()
            ));
        }

        ResponseWrapper<List<UserResponse>> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setContent(response);

        return responseWrapper;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
        } catch (AuthenticationException e) {
            System.err.println("e :" + e.getMessage());
            throw new BadCredentialsException("Invalid username or password");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + request.getUsername()));

        if (!user.isEnabled()) {
            throw new RuntimeException("User is not enabled");
        }
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole());
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

    public ResponseWrapper<?> editUser(UserRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + request.getUsername()));

        Field[] fields = request.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                if (field.get(request) != null) {

                    field.setAccessible(true);
                    if (!field.getName().equals("role")){
                        field.set(user, field.get(request));
                    }else{
                        field.set(user, roleRepository.findById(request.getRoleId()).orElseThrow(() -> new UsernameNotFoundException("Role not found!")));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        userRepository.save(user);
        logUtil.info("User with username: " + user.getUsername() + " has been edited successfully");
        return new ResponseWrapper<>(true, "User with username: " + user.getUsername() + " has been edited successfully");
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
