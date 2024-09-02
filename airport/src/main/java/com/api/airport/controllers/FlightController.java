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

import com.api.airport.models.entities.Flight;
import com.api.airport.models.payloads.MensajeResponse;
import com.api.airport.services.AirportService;
import com.api.airport.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<MensajeResponse> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Success")
                .object(flights)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeResponse> getFlightById(@PathVariable Integer id) {
        return flightService.findById(id)
                .map(flight -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Flight found")
                            .object(flight)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Flight not found")
                            .object(null)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }

    @PostMapping
    public ResponseEntity<MensajeResponse> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.save(flight);
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Flight created")
                .object(createdFlight)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> updateFlight(@PathVariable Integer id, @RequestBody Flight flight) {
        return flightService.findById(id)
                .map(existingFlight -> {
                    existingFlight.setAirline(flight.getAirline());
                    existingFlight.setWeekdays(flight.getWeekdays());
                    airportService.findById(flight.getDepartureAirport().getAirportCode())
                            .ifPresent(existingFlight::setDepartureAirport);
                    airportService.findById(flight.getArrivalAirport().getAirportCode())
                            .ifPresent(existingFlight::setArrivalAirport);
                    Flight updatedFlight = flightService.save(existingFlight);
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Flight updated")
                            .object(updatedFlight)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Flight not found")
                            .object(null)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> deleteFlight(@PathVariable Integer id) {
        if (flightService.findById(id).isPresent()) {
            flightService.deleteById(id);
            MensajeResponse response = MensajeResponse.builder()
                    .mensaje("Flight deleted")
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            MensajeResponse response = MensajeResponse.builder()
                    .mensaje("Flight not found")
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
