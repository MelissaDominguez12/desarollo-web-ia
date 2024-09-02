package com.api.airport.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.airport.models.entities.SeatReservation;
import com.api.airport.models.entities.pk.SeatReservationPK;
import com.api.airport.models.repositories.SeatReservationRepository;
import com.api.airport.services.SeatReservationService;

@Service
public class SeatReservationServiceImpl implements SeatReservationService {

    @Autowired
    private SeatReservationRepository seatReservationRepository;

    @Override
    public List<SeatReservation> findAll() {
        return (List<SeatReservation>) seatReservationRepository.findAll();
    }

    @Override
    public Optional<SeatReservation> findById(SeatReservationPK id) {
        return seatReservationRepository.findById(id);
    }

    @Override
    public SeatReservation save(SeatReservation seatReservation) {
        return seatReservationRepository.save(seatReservation);
    }

    @Override
    public void deleteById(SeatReservationPK id) {
        seatReservationRepository.deleteById(id);
    }
}
