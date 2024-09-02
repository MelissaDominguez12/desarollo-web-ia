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

import com.api.airport.models.entities.Fare;
import com.api.airport.models.entities.pk.FarePK;
import com.api.airport.models.payloads.MensajeResponse;
import com.api.airport.services.FareService;

@RestController
@RequestMapping("/fares")
public class FareController {

        @Autowired
        private FareService fareService;

        @GetMapping
        public ResponseEntity<MensajeResponse> getAllFares() {
                List<Fare> fares = fareService.findAll();
                MensajeResponse response = MensajeResponse.builder()
                        .mensaje("Success")
                        .object(fares)
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping("/{flightNumber}/{fareCode}")
        public ResponseEntity<MensajeResponse> getFareById(@PathVariable Integer flightNumber, @PathVariable String fareCode) {
                FarePK id = FarePK.builder()
                        .flightNumber(flightNumber)
                        .fareCode(fareCode)
                        .build();
                return fareService.findById(id)
                        .map(fare -> {
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Fare found")
                                        .object(fare)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Fare not found")
                                        .object(null)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
        }

        @PostMapping
        public ResponseEntity<MensajeResponse> createFare(@RequestBody Fare fare) {
        Fare createdFare = fareService.save(fare);
        MensajeResponse response = MensajeResponse.builder()
                .mensaje("Fare created")
                .object(createdFare)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        @PutMapping("/{flightNumber}/{fareCode}")
        public ResponseEntity<MensajeResponse> updateFare(@PathVariable Integer flightNumber, @PathVariable String fareCode, @RequestBody Fare fare) {
                FarePK id = FarePK.builder()
                        .flightNumber(flightNumber)
                        .fareCode(fareCode)
                        .build();
                return fareService.findById(id)
                        .map(existingFare -> {
                                existingFare.setAmount(fare.getAmount());
                                existingFare.setRestrictions(fare.getRestrictions());
                                Fare updatedFare = fareService.save(existingFare);
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Fare updated")
                                        .object(updatedFare)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.OK);
                        })
                        .orElseGet(() -> {
                                MensajeResponse response = MensajeResponse.builder()
                                        .mensaje("Fare not found")
                                        .object(null)
                                        .build();
                                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                        });
                }

        @DeleteMapping("/{flightNumber}/{fareCode}")
        public ResponseEntity<MensajeResponse> deleteFare(@PathVariable Integer flightNumber, @PathVariable String fareCode) {
                FarePK id = FarePK.builder()
                        .flightNumber(flightNumber)
                        .fareCode(fareCode)
                        .build();
                if (fareService.findById(id).isPresent()) {
                        fareService.deleteById(id);
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Fare deleted")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                } else {
                        MensajeResponse response = MensajeResponse.builder()
                                .mensaje("Fare not found")
                                .object(null)
                                .build();
                        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
        }
}
