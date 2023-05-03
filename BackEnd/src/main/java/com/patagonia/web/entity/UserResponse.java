package com.patagonia.web.entity;

public class UserResponse {

    private Long id;
    private String username;
    private String name;
    private int age;
    private String email;
    private String address;
    private int status;
    private int creditScore;
    private boolean enabled;
    private Role role;


    public UserResponse() {
    }

    public UserResponse(Long id, String username, String name, int age, String email, String address, int status, int creditScore, boolean enabled, Role role) {
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", creditScore=" + creditScore +
                '}';
    }
}
