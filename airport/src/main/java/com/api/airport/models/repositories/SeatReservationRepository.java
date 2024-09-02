package com.api.airport.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.api.airport.models.entities.SeatReservation;
import com.api.airport.models.entities.pk.SeatReservationPK;

public interface SeatReservationRepository extends CrudRepository<SeatReservation, SeatReservationPK>{

}
