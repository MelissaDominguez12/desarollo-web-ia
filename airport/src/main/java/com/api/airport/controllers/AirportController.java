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

import com.api.airport.models.entities.Airport;
import com.api.airport.models.payloads.MensajeResponse;
import com.api.airport.services.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<MensajeResponse> getAllAirports() {
        List<Airport> airports = airportService.findAll();
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Success")
                .object(airports)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeResponse> getAirportById(@PathVariable String id) {
        return airportService.findById(id)
                .map(airport -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Airport found")
                            .object(airport)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Airport not found")
                            .object(null)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }

    @PostMapping
    public ResponseEntity<MensajeResponse> createAirport(@RequestBody Airport airport) {
        Airport createdAirport = airportService.save(airport);
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Airport created")
                .object(createdAirport)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> updateAirport(@PathVariable String id, @RequestBody Airport airport) {
        return airportService.findById(id)
                .map(existingAirport -> {
                    existingAirport.setAirportName(airport.getAirportName());
                    existingAirport.setCity(airport.getCity());
                    existingAirport.setState(airport.getState());
                    Airport updatedAirport = airportService.save(existingAirport);
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Airport updated")
                            .object(updatedAirport)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    MensajeResponse response = MensajeResponse.builder()
                            .mensaje("Airport not found")
                            .object(null)
                            .build();
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> deleteAirport(@PathVariable String id) {
        if (airportService.findById(id).isPresent()) {
            airportService.deleteById(id);
            MensajeResponse response = MensajeResponse.builder()
                    .mensaje("Airport deleted")
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            MensajeResponse response = MensajeResponse.builder()
                    .mensaje("Airport not found")
                    .object(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
