package com.api.airport.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.airport.models.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, Integer>{

}
