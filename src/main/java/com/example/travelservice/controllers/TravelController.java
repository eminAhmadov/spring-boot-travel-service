package com.example.travelservice.controllers;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.services.TravelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Travel createTravel(@RequestBody Travel travel) {
        return travelService.createTravel(travel);
    }

}
