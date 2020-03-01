package com.example.travelservice.controllers;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.services.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Travel> getAll() {
        return travelService.getAll();
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
    public ResponseEntity<String> editTravel (@RequestParam Long id, @RequestBody Travel travel) {
        return travelService.editTravel(id, travel);
    }

}
