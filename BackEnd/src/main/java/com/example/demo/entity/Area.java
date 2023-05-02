package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "Area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Area_Id")
    private Integer areaId;

    @Column(name = "No_Properties")
    private Integer noProperties;

    @Column(name = "Location")
    private String location;

    // Constructors, getters and setters

    public Area() {
    }

    public Area(Integer noProperties, String location) {
        this.noProperties = noProperties;
        this.location = location;
    }

    // Getters and setters

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getNoProperties() {
        return noProperties;
    }

    public void setNoProperties(Integer noProperties) {
        this.noProperties = noProperties;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}