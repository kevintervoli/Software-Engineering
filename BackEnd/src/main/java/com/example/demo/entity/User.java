package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    protected String Name;
    @Column(nullable = false)
    protected String Surname;

    @Column(nullable = false)
    protected int Age;

    @Column(nullable = false)
    protected String Email;

    @Column(nullable = false)
    protected String Address;

    @Column(nullable = false)
    protected String username;

    @Column(nullable = false)
    protected String Password;

    @Column(nullable = false)
    protected int Status;

    @Column(nullable = false)
    protected int Credit_Score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Admin_ID", referencedColumnName = "ID")
    protected Admin admin;

    public User(String firstName, String lastName,String username,String email,String address,String password,int status, int Cred_Score) {
        this.Name= firstName;
        this.Surname= lastName;
        this.username=username;
        this.Email=email;
        this.Address=address;
        this.Password=password;
        this.Status=status;
        this.Credit_Score=Cred_Score;

    }

    public User() {
        this.Name= "";
        this.Surname= "";
        this.username="";
        this.Email="";
        this.Address="";
        this.Password="";
        this.Status=0;
        this.Credit_Score=0;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getCredit_Score() {
        return Credit_Score;
    }

    public void setCredit_Score(int Credit_Score) {
        this.Credit_Score = Credit_Score;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
