package com.patagonia.web.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Auction_ID", nullable = false)
    private Long auctionId;

    @Column(name = "Auction_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date auctionDate;

    @Column(name = "Auction_Venue", nullable = false, length = 255)
    private String auctionVenue;

    @Column(name = "Auction_InitialPrice", nullable = false)
    private Integer auctionInitialPrice;

    @Column(name = "Auctioneer", nullable = false, length = 255)
    private String auctioneer;


    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
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

    public String getAuctioneer() {
        return auctioneer;
    }

    public void setAuctioneer(String auctioneer) {
        this.auctioneer = auctioneer;
    }
}
