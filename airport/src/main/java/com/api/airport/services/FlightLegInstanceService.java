package com.api.airport.services;

import java.util.List;
import java.util.Optional;

import com.api.airport.models.entities.FlightLegInstance;
import com.api.airport.models.entities.pk.FlightLegInstancePK;

public interface FlightLegInstanceService {
    List<FlightLegInstance> findAll();
    Optional<FlightLegInstance> findById(FlightLegInstancePK id);
    FlightLegInstance save(FlightLegInstance flightLegInstance);
    void deleteById(FlightLegInstancePK id);
}
