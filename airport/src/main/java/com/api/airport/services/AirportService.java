package com.api.airport.services;

import java.util.List;
import java.util.Optional;

import com.api.airport.models.entities.Airport;

public interface AirportService {
    List<Airport> findAll();
    Optional<Airport> findById(String airportCode);
    Airport save(Airport airport);
    void deleteById(String airportCode);
}

