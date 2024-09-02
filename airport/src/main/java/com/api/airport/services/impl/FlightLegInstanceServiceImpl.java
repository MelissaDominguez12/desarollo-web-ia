package com.api.airport.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.airport.models.entities.FlightLegInstance;
import com.api.airport.models.entities.pk.FlightLegInstancePK;
import com.api.airport.models.repositories.FlightLegInstanceRepository;
import com.api.airport.services.FlightLegInstanceService;

@Service
public class FlightLegInstanceServiceImpl implements FlightLegInstanceService {

    @Autowired
    private FlightLegInstanceRepository flightLegInstanceRepository;

    @Override
    public List<FlightLegInstance> findAll() {
        return (List<FlightLegInstance>) flightLegInstanceRepository.findAll();
    }

    @Override
    public Optional<FlightLegInstance> findById(FlightLegInstancePK id) {
        return flightLegInstanceRepository.findById(id);
    }

    @Override
    public FlightLegInstance save(FlightLegInstance flightLegInstance) {
        return flightLegInstanceRepository.save(flightLegInstance);
    }

    @Override
    public void deleteById(FlightLegInstancePK id) {
        flightLegInstanceRepository.deleteById(id);
    }
}