package com.api.airport.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.airport.models.entities.Flight;
import com.api.airport.models.repositories.FlightRepository;
import com.api.airport.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return (List<Flight>) flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(Integer flightNumber) {
        return flightRepository.findById(flightNumber);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Integer flightNumber) {
        flightRepository.deleteById(flightNumber);
    }
}
