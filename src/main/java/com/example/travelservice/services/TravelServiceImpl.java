package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.repositories.TravelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    final
    TravelRepository travelRepository;

    public TravelServiceImpl(
            TravelRepository travelRepository
    ) {
        this.travelRepository = travelRepository;
    }

    @Override
    public List<Travel> getAllT() {
        return travelRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Travel createTravel(Travel travel) {
        return travelRepository.save(travel);
    }
}
