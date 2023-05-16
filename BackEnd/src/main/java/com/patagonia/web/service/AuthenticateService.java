package com.patagonia.web.service;

import com.patagonia.web.entity.*;
import com.patagonia.web.enums.RoleEnum;
import com.patagonia.web.repository.AdminRepository;
import com.patagonia.web.repository.AgentRepository;
import com.patagonia.web.repository.RoleRepository;
import com.patagonia.web.repository.UserRepository;
import com.patagonia.web.util.LogUtil;
import lombok.var;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
    private final AdminRepository adminRepository;
    private final AgentRepository agentRepository;

    public AuthenticateService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, LogUtil logUtil, AdminRepository adminRepository, AgentRepository agentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.logUtil = logUtil;
        this.adminRepository = adminRepository;
        this.agentRepository = agentRepository;
    }


    public AuthenticationResponse registerUser(UserRequest request) {

        boolean userExists = userRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean adminExists = adminRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean agentExists = agentRepository.findByUsername(request.getUsername())
                .isPresent();

        if (userExists || adminExists || agentExists) {
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
                roleRepository.findByName(RoleEnum.USER.name())
        );

        userRepository.save(user);


        logUtil.info("User with username: " + user.getUsername() + " has been registered successfully");
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        return new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole());

    }

    public AuthenticationResponse registerAdmin(AdminRequest request) {

            boolean userExists = userRepository.findByUsername(request.getUsername())
                    .isPresent();

            boolean adminExists = adminRepository.findByUsername(request.getUsername())
                    .isPresent();

            boolean agentExists = agentRepository.findByUsername(request.getUsername())
                    .isPresent();

            if (userExists || adminExists || agentExists) {
                logUtil.warn("username already taken");
                throw new IllegalStateException("username already taken");
            }

            var admin = new Admin(
                    request.getName(),
                    request.getSurname(),
                    request.getUsername(),
                    passwordEncoder.encode(request.getPassword()),
                    request.getEmail(),
                    request.getAddress(),
                    request.getGender(),
                    request.getAge(),
                    roleRepository.findByName(RoleEnum.ADMIN.name()),
                    request.isEnabled()
            );

            adminRepository.save(admin);

            logUtil.info("User with username: " + admin.getUsername() + " has been registered successfully");
            var jwtToken = jwtService.generateToken(admin.getUsername(), admin.getRole());
            return new AuthenticationResponse(admin.getUsername(), jwtToken, admin.getRole());
    }

    public ResponseWrapper<T> createUser(UserRequest request) {

        boolean userExists = userRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean adminExists = adminRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean agentExists = agentRepository.findByUsername(request.getUsername())
                .isPresent();

        if (userExists || adminExists || agentExists) {
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
                    roleRepository.findByName(RoleEnum.USER.name())
            );

            userRepository.save(user);

            logUtil.info("User with username: " + user.getUsername() + " has been registered successfully");
            var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
            List<AuthenticationResponse> content = new ArrayList<>();
            content.add(new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole(), true));
            return new ResponseWrapper<>(true, content);
    }

    public ResponseWrapper<T> createAdmin(AdminRequest request) {

        boolean userExists = userRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean adminExists = adminRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean agentExists = agentRepository.findByUsername(request.getUsername())
                .isPresent();

        if (userExists || adminExists || agentExists) {
            logUtil.warn("username already taken");
            throw new IllegalStateException("username already taken");
        }

        var admin = new Admin(
                request.getName(),
                request.getSurname(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                request.getAddress(),
                request.getGender(),
                request.getAge(),
                roleRepository.findByName(RoleEnum.ADMIN.name()),
                request.isEnabled()
        );

        adminRepository.save(admin);

        logUtil.info("User with username: " + admin.getUsername() + " has been registered successfully");
        var jwtToken = jwtService.generateToken(admin.getUsername(), admin.getRole());
        List<AuthenticationResponse> content = new ArrayList<>();
        content.add(new AuthenticationResponse(admin.getUsername(), jwtToken, admin.getRole(), true));
        return new ResponseWrapper<>(true, content);
    }

    public ResponseWrapper<T> createAgent(AgentRequest request) {

        boolean userExists = userRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean adminExists = adminRepository.findByUsername(request.getUsername())
                .isPresent();

        boolean agentExists = agentRepository.findByUsername(request.getUsername())
                .isPresent();

        if (userExists || adminExists || agentExists) {
            logUtil.warn("username already taken");
            throw new IllegalStateException("username already taken");
        }

        Agent agent = new Agent();
        agent.setSalary(request.getSalary());
        agent.setName(request.getName());
        agent.setSurname(request.getSurname());
        agent.setUsername(request.getUsername());
        agent.setPassword(passwordEncoder.encode(request.getPassword()));
        agent.setEmail(request.getEmail());
        agent.setAddress(request.getAddress());
        agent.setGender(request.getGender());
        agent.setAge(request.getAge());
        agent.setId(request.getId());
        agent.setRole(roleRepository.findByName(RoleEnum.AGENT.name()));
        agent.setEnabled(request.isEnabled());


        agentRepository.save(agent);
        String successMessage = "Agent with username: " + agent.getUsername() + " has been created successfully";
        logUtil.info(successMessage);
        return new ResponseWrapper<>(true, successMessage);
    }

    public ResponseWrapper<List<UserResponse>>getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();

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

    public ResponseWrapper<T>authenticate(AuthenticationRequest request){
        ResponseWrapper<T> responseAdmin = doAuth(request, adminRepository, Admin.class);
        ResponseWrapper<T> responseUser = doAuth(request, userRepository, User.class);
        ResponseWrapper<T> responseAgent = doAuth(request, agentRepository, Agent.class);
        if (responseUser.isStatus()){
            logUtil.info("User with username: " + request.getUsername() + " has been authenticated successfully", "AUTHENTICATE");
            return responseUser;
        } else if (responseAdmin.isStatus()){
            logUtil.info("Admin with username: " + request.getUsername() + " has been authenticated successfully", "AUTHENTICATE");
            return responseAdmin;
        } else if (responseAgent.isStatus()){
            logUtil.info("Agent with username: " + request.getUsername() + " has been authenticated successfully", "AUTHENTICATE");
            return responseAgent;
        } else {
            logUtil.warn("Invalid username or password", "AUTHENTICATE");
            return new ResponseWrapper<>(false, "Invalid username or password");
        }
    }


    public <U extends AuthenticationResponse> ResponseWrapper<T> doAuth(AuthenticationRequest request, JpaSpecificationExecutor<U> userRepository, Class<U> userClass) {
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

        U user = userRepository.findOne((root, query, cb) -> cb.equal(root.get("username"), request.getUsername())).isPresent() ?
                userRepository.findOne((root, query, cb) -> cb.equal(root.get("username"), request.getUsername())).get() : null;

        if (user == null) {
            return new ResponseWrapper<>(false, "User not found");
        }else if(!user.isEnabled()) {
            return new ResponseWrapper<>(false, "User is not enabled");
        }
        var jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(user.getUsername(), jwtToken, user.getRole(), user.isEnabled());
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
