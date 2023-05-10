package com.patagonia.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Agent_Area")
public class AgentArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RelationID", nullable = false)
    private Long relationId;

    @ManyToOne
    @JoinColumn(name = "Agent_ID", referencedColumnName = "Agent_ID", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "Area_Id", referencedColumnName = "Area_Id", nullable = false)
    private Area area;

    // Getters and Setters

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
