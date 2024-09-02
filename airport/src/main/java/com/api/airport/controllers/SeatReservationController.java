package com.api.airport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.airport.models.entities.SeatReservation;
import com.api.airport.models.entities.pk.SeatReservationPK;
import com.api.airport.models.payloads.MensajeResponse;
import com.api.airport.services.SeatReservationService;

@RestController
@RequestMapping("/seat-reservations")
public class SeatReservationController {

        @Autowired
        private SeatReservationService seatReservationService;

        @GetMapping
        public ResponseEntity<MensajeResponse> getAllSeatReservations() {
                List<SeatReservation> seatReservations = seatReservationService.findAll();
                        MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Success")
                        .object(seatReservations)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/{flightNumber}/{legNumber}/{legDate}/{seatNumber}")
        public ResponseEntity<MensajeResponse> getSeatReservationById(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate,
                @PathVariable String seatNumber) {
                SeatReservationPK id = SeatReservationPK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .seatNumber(seatNumber)
                        .build();
                return seatReservationService.findById(id)
                        .map(seatReservation -> {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Seat reservation found")
                                .object(seatReservation)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Seat reservation not found")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
        }

        @PostMapping
        public ResponseEntity<MensajeResponse> createSeatReservation(@RequestBody SeatReservation seatReservation) {
                SeatReservation createdSeatReservation = seatReservationService.save(seatReservation);
                MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Seat reservation created")
                        .object(createdSeatReservation)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @PutMapping("/{flightNumber}/{legNumber}/{legDate}/{seatNumber}")
        public ResponseEntity<MensajeResponse> updateSeatReservation(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate,
                @PathVariable String seatNumber,
                @RequestBody SeatReservation seatReservation) {
                SeatReservationPK id = SeatReservationPK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .seatNumber(seatNumber)
                        .build();
                return seatReservationService.findById(id)
                        .map(existingSeatReservation -> {
                        existingSeatReservation.setCustomerName(seatReservation.getCustomerName());
                        existingSeatReservation.setCustomerPhone(seatReservation.getCustomerPhone());
                        SeatReservation updatedSeatReservation = seatReservationService.save(existingSeatReservation);
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Seat reservation updated")
                                .object(updatedSeatReservation)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Seat reservation not found")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
        }

        @DeleteMapping("/{flightNumber}/{legNumber}/{legDate}/{seatNumber}")
        public ResponseEntity<MensajeResponse> deleteSeatReservation(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate,
                @PathVariable String seatNumber) {
                SeatReservationPK id = SeatReservationPK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .seatNumber(seatNumber)
                        .build();
                if (seatReservationService.findById(id).isPresent()) {
                seatReservationService.deleteById(id);
                MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Seat reservation deleted")
                        .object(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                } else {
                MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Seat reservation not found")
                        .object(null)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
        }
}
