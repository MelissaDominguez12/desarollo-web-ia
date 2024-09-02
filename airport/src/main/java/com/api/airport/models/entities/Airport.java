package com.api.airport.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "AIRPORT")
@Data
public class Airport {
    
    @Id
    private String airportCode;
    
    private String airportName;
    
    private String city;
    
    private String state;
}
