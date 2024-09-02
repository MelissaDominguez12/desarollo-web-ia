package com.api.airport.models.entities.pk;

import java.io.Serializable;
import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightLegInstancePK implements Serializable {
    private Integer flightNumber;
    private Integer legNumber;
    private Date legDate;
}