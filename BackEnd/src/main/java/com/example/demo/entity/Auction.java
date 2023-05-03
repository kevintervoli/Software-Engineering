package com.example.demo.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "Auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Auction_ID")
    private Integer auctionId;

    @Column(name = "Auction_Date")
    private LocalDate auctionDate;

    @Column(name = "Auction_Venue")
    private String auctionVenue;

    @Column(name = "Auction_InitialPrice")
    private Integer auctionInitialPrice;

    @Column(name = "Auctioneer")
    private Integer auctioneer;

    public Auction() {
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public LocalDate getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(LocalDate auctionDate) {
        this.auctionDate = auctionDate;
    }

    public String getAuctionVenue() {
        return auctionVenue;
    }

    public void setAuctionVenue(String auctionVenue) {
        this.auctionVenue = auctionVenue;
    }

    public Integer getAuctionInitialPrice() {
        return auctionInitialPrice;
    }

    public void setAuctionInitialPrice(Integer auctionInitialPrice) {
        this.auctionInitialPrice = auctionInitialPrice;
    }

    public Integer getAuctioneer() {
        return auctioneer;
    }

    public void setAuctioneer(Integer auctioneer) {
        this.auctioneer = auctioneer;
    }

    public Auction(Integer auctionId, LocalDate auctionDate, String auctionVenue, Integer auctionInitialPrice, Integer auctioneer) {
        this.auctionId = auctionId;
        this.auctionDate = auctionDate;
        this.auctionVenue = auctionVenue;
        this.auctionInitialPrice = auctionInitialPrice;
        this.auctioneer = auctioneer;

    }
}
