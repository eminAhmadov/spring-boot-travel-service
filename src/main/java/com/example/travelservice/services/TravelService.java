package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TravelService {
    List<Travel> getAll(int offset, int limit);
    List<Travel> getAllForUserWithUserId(UUID userId, int offset, int limit);
    List<Travel> search(String origin, String destination, Date fromDate, Date toDate, int offset, int limit);
    ResponseEntity<Travel> createTravel(Travel travel);
    ResponseEntity<String> deleteTravel(Long id);
    ResponseEntity<String> editTravel(Long id, Travel travel);
}
