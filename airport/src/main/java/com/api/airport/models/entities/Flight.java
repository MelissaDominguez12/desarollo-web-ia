package com.api.airport.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {
    
    @Id
    private Integer flightNumber;
    
    private String airline;
    
    private String weekdays;
    
    @ManyToOne
    @JoinColumn(name = "departureAirportCode", foreignKey = @ForeignKey(name = "FK_DEPARTURE_AIRPORT"))
    private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name = "arrivalAirportCode", foreignKey = @ForeignKey(name = "FK_ARRIVAL_AIRPORT"))
    private Airport arrivalAirport;
}