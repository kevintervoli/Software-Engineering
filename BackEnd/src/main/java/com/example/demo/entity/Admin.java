package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Surname", nullable = false, length = 255)
    private String surname;

    @Column(name = "Username", nullable = false, length = 255)
    private String username;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "Email", length = 255)
    private String email;


    @Column(name = "Address", nullable = false, length = 255)
    private String address;

    @Column(name = "Gender", nullable = false, length = 255)
    private String gender;

    @Column(name = "Age", nullable = false)
    private int age;

    public Admin(int ID, String name, String surname, String username, String password, String email, String address, String gender, int age) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.age = age;
    }
    public Admin() {
        this.ID = 0;
        this.name = "";
        this.surname = "";
        this.username = "";
        this.password = "";
        this.email = "";
        this.address = "";
        this.gender = "";
        this.age = 0;
    }
//    create constructor

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() { return surname; }
    public void setUsername(String username) {
        this.username = username;
    }
public String getUsername() { return username; }


}
