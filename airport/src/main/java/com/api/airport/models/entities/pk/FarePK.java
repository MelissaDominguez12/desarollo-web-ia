package com.api.airport.models.entities.pk;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarePK implements Serializable {
    private Integer flightNumber;
    private String fareCode;
}