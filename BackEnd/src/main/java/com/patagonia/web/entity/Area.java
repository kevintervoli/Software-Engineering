package com.patagonia.web.entity;
import jakarta.persistence.*;

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
