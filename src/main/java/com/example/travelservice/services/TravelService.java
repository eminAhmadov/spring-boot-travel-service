package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TravelService {
    List<Travel> getAll();
    List<Travel> getAllForUserWithUserId(UUID userId);
    ResponseEntity<Travel> createTravel(Travel travel);
    ResponseEntity<String> deleteTravel(Long id);
    ResponseEntity<String> editTravel(Long id, Travel travel);
}
