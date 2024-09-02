package com.api.airport.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.airport.models.entities.Fare;
import com.api.airport.models.entities.pk.FarePK;
import com.api.airport.models.repositories.FareRepository;
import com.api.airport.services.FareService;

@Service
public class FareServiceImpl implements FareService {

    @Autowired
    private FareRepository fareRepository;

    @Override
    public List<Fare> findAll() {
        return (List<Fare>) fareRepository.findAll();
    }

    @Override
    public Optional<Fare> findById(FarePK id) {
        return fareRepository.findById(id);
    }

    @Override
    public Fare save(Fare fare) {
        return fareRepository.save(fare);
    }

    @Override
    public void deleteById(FarePK id) {
        fareRepository.deleteById(id);
    }
}
