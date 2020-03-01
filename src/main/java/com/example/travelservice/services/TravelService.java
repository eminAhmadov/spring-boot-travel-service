package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TravelService {
    List<Travel> getAll();
    Travel createTravel(Travel travel);
    ResponseEntity<String> deleteTravel(Long id);
}
