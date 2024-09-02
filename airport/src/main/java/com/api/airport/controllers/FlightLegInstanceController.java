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

import com.api.airport.models.entities.FlightLegInstance;
import com.api.airport.models.entities.pk.FlightLegInstancePK;
import com.api.airport.models.payloads.MensajeResponse;
import com.api.airport.services.FlightLegInstanceService;

@RestController
@RequestMapping("/flight-leg-instances")
public class FlightLegInstanceController {

        @Autowired
        private FlightLegInstanceService flightLegInstanceService;

        @GetMapping
        public ResponseEntity<MensajeResponse> getAllFlightLegInstances() {
        List<FlightLegInstance> flightLegInstances = flightLegInstanceService.findAll();
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Success")
                .object(flightLegInstances)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/{flightNumber}/{legNumber}/{legDate}")
        public ResponseEntity<MensajeResponse> getFlightLegInstanceById(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate) {
                FlightLegInstancePK id = FlightLegInstancePK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .build();
                return flightLegInstanceService.findById(id)
                        .map(flightLegInstance -> {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Flight leg instance found")
                                .object(flightLegInstance)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Flight leg instance not found")
                                        .object(null)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
        }

        @PostMapping
        public ResponseEntity<MensajeResponse> createFlightLegInstance(@RequestBody FlightLegInstance flightLegInstance) {
                FlightLegInstance createdFlightLegInstance = flightLegInstanceService.save(flightLegInstance);
                MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Flight leg instance created")
                        .object(createdFlightLegInstance)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.CREATED);
                }

        @PutMapping("/{flightNumber}/{legNumber}/{legDate}")
        public ResponseEntity<MensajeResponse> updateFlightLegInstance(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate,
                @RequestBody FlightLegInstance flightLegInstance) {
                FlightLegInstancePK id = FlightLegInstancePK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .build();
                return flightLegInstanceService.findById(id)
                        .map(existingFlightLegInstance -> {
                                existingFlightLegInstance.setNumberOfAvailableSeats(flightLegInstance.getNumberOfAvailableSeats());
                                existingFlightLegInstance.setAirplaneId(flightLegInstance.getAirplaneId());
                                existingFlightLegInstance.setDepartureTime(flightLegInstance.getDepartureTime());
                                existingFlightLegInstance.setArrivalTime(flightLegInstance.getArrivalTime());
                                FlightLegInstance updatedFlightLegInstance = flightLegInstanceService.save(existingFlightLegInstance);
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Flight leg instance updated")
                                        .object(updatedFlightLegInstance)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Flight leg instance not found")
                                        .object(null)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
        }

        @DeleteMapping("/{flightNumber}/{legNumber}/{legDate}")
        public ResponseEntity<MensajeResponse> deleteFlightLegInstance(
                @PathVariable Integer flightNumber,
                @PathVariable Integer legNumber,
                @PathVariable String legDate) {
                FlightLegInstancePK id = FlightLegInstancePK.builder()
                        .flightNumber(flightNumber)
                        .legNumber(legNumber)
                        .legDate(java.sql.Date.valueOf(legDate))
                        .build();
                if (flightLegInstanceService.findById(id).isPresent()) {
                        flightLegInstanceService.deleteById(id);
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Flight leg instance deleted")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                } else {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Flight leg instance not found")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
        }
}
