package com.api.airport.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.airport.models.entities.FlightLegInstance;
import com.api.airport.models.entities.pk.FlightLegInstancePK;

public interface FlightLegInstanceRepository extends CrudRepository<FlightLegInstance, FlightLegInstancePK>{

}
