package com.api.airport.services;

import java.util.List;
import java.util.Optional;

import com.api.airport.models.entities.Fare;
import com.api.airport.models.entities.pk.FarePK;

public interface FareService {
    List<Fare> findAll();
    Optional<Fare> findById(FarePK id);
    Fare save(Fare fare);
    void deleteById(FarePK id);
}
