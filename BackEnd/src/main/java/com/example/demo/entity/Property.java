package com.example.demo.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "Property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Property_Id")
    private int propertyId;

    @Column(name = "Property_Name")
    private String propertyName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Nr_Bedrooms")
    private int numberOfBedrooms;

    @Column(name = "Nr_Bathrooms")
    private int numberOfBathrooms;

    @ManyToOne
    @JoinColumn(name = "Area_Id")
    private Area area;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private int price;

    @Column(name = "No_Garages")
    private int numberOfGarages;

    @Column(name = "Stories")
    private int numberOfStories;

    @Column(name = "Status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "Agent_ID")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "Auction_ID")
    private Auction auction;


    public Property(String propertyName, String description, int numberOfBedrooms, int numberOfBathrooms, Area area, String image, int price, int numberOfGarages, int numberOfStories, int status, Agent agent, Auction auction) {
        this.propertyName = propertyName;
        this.description = description;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.area = area;
        this.image = image;
        this.price = price;
        this.numberOfGarages = numberOfGarages;
        this.numberOfStories = numberOfStories;
        this.status = status;
        this.agent = agent;
        this.auction = auction;
    }

    public Property() {
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfGarages() {
        return numberOfGarages;
    }

    public void setNumberOfGarages(int numberOfGarages) {
        this.numberOfGarages = numberOfGarages;
    }

    public int getNumberOfStories() {
        return numberOfStories;
    }

    public void setNumberOfStories(int numberOfStories) {
        this.numberOfStories = numberOfStories;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
