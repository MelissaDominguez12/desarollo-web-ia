package com.api.airport.services;

import java.util.List;
import java.util.Optional;

import com.api.airport.models.entities.Flight;

public interface FlightService {
    List<Flight> findAll();
    Optional<Flight> findById(Integer flightNumber);
    Flight save(Flight flight);
    void deleteById(Integer flightNumber);
}
