package com.patagonia.web.entity;
import jakarta.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "Property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Property_Id")
    private Long propertyId;

    @Column(name = "Property_Name")
    private String propertyName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Nr_Bedrooms")
    private Integer nrBedrooms;

    @Column(name = "Nr_Bathrooms")
    private Integer nrBathrooms;

    @Column(name = "Area")
    private String area;

    @Column(name = "Image")
    private byte[] image;

    @Column(name = "Price")
    private Integer price;

    @Column(name = "No_Garages")
    private Integer noGarages;

    @Column(name = "Stories")
    private Integer stories;

    @Column(name = "Status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "Agent_ID", referencedColumnName = "Agent_ID")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "Area_Id", referencedColumnName = "Area_Id")
    private Area areaEntity;

    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Auction_ID", referencedColumnName = "Auction_ID")
    private Auction auction;

    // Getters and Setters

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
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

    public Integer getNrBedrooms() {
        return nrBedrooms;
    }

    public void setNrBedrooms(Integer nrBedrooms) {
        this.nrBedrooms = nrBedrooms;
    }

    public Integer getNrBathrooms() {
        return nrBathrooms;
    }

    public void setNrBathrooms(Integer nrBathrooms) {
        this.nrBathrooms = nrBathrooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNoGarages() {
        return noGarages;
    }

    public void setNoGarages(Integer noGarages) {
        this.noGarages = noGarages;
    }

    public Integer getStories() {
        return stories;
    }

    public void setStories(Integer stories) {
        this.stories = stories;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Area getAreaEntity() {
        return areaEntity;
    }

    public void setAreaEntity(Area areaEntity) {
        this.areaEntity = areaEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

}
