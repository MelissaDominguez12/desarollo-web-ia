package com.api.airport.models.entities;

import java.util.Date;

import com.api.airport.models.entities.pk.FlightLegInstancePK;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FLIGHT_LEG_INSTANCE")
@Data
@IdClass(FlightLegInstancePK.class)
public class FlightLegInstance {

    @Id
    private Integer flightNumber;
    
    @Id
    private Integer legNumber;
    
    @Id
    private Date legDate;
    
    private Integer numberOfAvailableSeats;
    
    private Integer airplaneId;
    
    private Date departureTime;
    
    private Date arrivalTime;
    
    @ManyToOne
    @JoinColumn(name = "flightNumber", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_FLIGHT_LEG_INSTANCE_FLIGHT"))
    private Flight flight;
}


