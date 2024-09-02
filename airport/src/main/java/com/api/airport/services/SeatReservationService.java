package com.api.airport.services;

import java.util.List;
import java.util.Optional;

import com.api.airport.models.entities.SeatReservation;
import com.api.airport.models.entities.pk.SeatReservationPK;

public interface SeatReservationService {
    List<SeatReservation> findAll();
    Optional<SeatReservation> findById(SeatReservationPK id);
    SeatReservation save(SeatReservation seatReservation);
    void deleteById(SeatReservationPK id);
}

