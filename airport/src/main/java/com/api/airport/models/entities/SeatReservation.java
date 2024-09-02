package com.api.airport.models.entities;

import java.util.Date;

import com.api.airport.models.entities.pk.SeatReservationPK;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SEAT_RESERVATION")
@Data
@IdClass(SeatReservationPK.class)
public class SeatReservation {

    @Id
    private Integer flightNumber;
    
    @Id
    private Integer legNumber;
    
    @Id
    private Date legDate;
    
    @Id
    private String seatNumber;
    
    private String customerName;
    
    private String customerPhone;
    
    @ManyToOne
    @JoinColumn(name = "flightNumber", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_SEAT_RESERVATION_FLIGHT_LEG_INSTANCE"))
    @JoinColumn(name = "legNumber", insertable = false, updatable = false)
    @JoinColumn(name = "legDate", insertable = false, updatable = false)
    private FlightLegInstance flightLegInstance;
}

