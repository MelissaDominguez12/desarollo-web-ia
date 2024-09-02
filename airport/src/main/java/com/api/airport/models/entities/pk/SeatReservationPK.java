package com.api.airport.models.entities.pk;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatReservationPK implements Serializable {
    private Integer flightNumber;
    private Integer legNumber;
    private Date legDate;
    private String seatNumber;
}

