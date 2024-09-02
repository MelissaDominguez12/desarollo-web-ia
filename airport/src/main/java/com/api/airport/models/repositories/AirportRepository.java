package com.api.airport.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.airport.models.entities.Airport;

public interface AirportRepository extends CrudRepository <Airport, String>{

}
