package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;

import java.util.List;

public interface TravelService {
    List<Travel> getAllT();
    Travel createTravel(Travel travel);
}
