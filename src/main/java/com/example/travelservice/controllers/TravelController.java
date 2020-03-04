package com.example.travelservice.controllers;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.services.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TravelController {

    final
    TravelService travelService;

    public TravelController(
            TravelService travelService
    ) {
        this.travelService = travelService;
    }

    @GetMapping("/getAll")
    public List<Travel> getAll(
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "limit", defaultValue = "5", required = false) int limit
    ) {
        return travelService.getAll(offset, limit);
    }

    @GetMapping("/getAllForUserWithUserId")
    public List<Travel> getAllForUserWithUserId(
            @RequestParam UUID userId,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "limit", defaultValue = "5", required = false) int limit
    ) {
        return travelService.getAllForUserWithUserId(userId, offset, limit);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Travel> createTravel(@RequestBody Travel travel) {
        return travelService.createTravel(travel);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteTravel(@RequestParam Long id) {
        return travelService.deleteTravel(id);
    }

    @PutMapping(path = "/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editTravel(@RequestParam Long id, @RequestBody Travel travel) {
        return travelService.editTravel(id, travel);
    }

}
