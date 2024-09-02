package com.api.airport.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.airport.models.entities.Fare;
import com.api.airport.models.entities.pk.FarePK;

public interface FareRepository extends CrudRepository<Fare, FarePK>{

}
