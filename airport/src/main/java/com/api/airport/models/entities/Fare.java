package com.api.airport.models.entities;

import com.api.airport.models.entities.pk.FarePK;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FARE")
@Data
@IdClass(FarePK.class)
public class Fare {
    
    @Id
    private Integer flightNumber;
    
    @Id
    private String fareCode;
    
    private Double amount;
    
    private String restrictions;
    
    @ManyToOne
    @JoinColumn(name = "flightNumber", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_FARE_FLIGHT"))
    private Flight flight;
}
