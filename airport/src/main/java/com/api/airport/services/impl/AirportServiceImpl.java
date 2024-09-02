package com.api.airport.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.airport.models.entities.Airport;
import com.api.airport.models.repositories.AirportRepository;
import com.api.airport.services.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return (List<Airport>) airportRepository.findAll();
    }

    @Override
    public Optional<Airport> findById(String airportCode) {
        return airportRepository.findById(airportCode);
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public void deleteById(String airportCode) {
        airportRepository.deleteById(airportCode);
    }
}
