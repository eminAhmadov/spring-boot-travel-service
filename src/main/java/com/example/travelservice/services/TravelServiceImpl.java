package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.repositories.TravelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public List<Travel> getAll() {
        return travelRepository.findAllByOrderByDateDesc();
    }

    @Override
    public Travel createTravel(Travel travel) {
        return travelRepository.save(travel);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteTravel(Long id) {
        String message;
        Optional<Travel> foundTravel = travelRepository.findById(id);

        if (foundTravel.isPresent()) {
            travelRepository.deleteById(id);
            message = "Successfully deleted travel with id: " + id + ".";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        message = "Could not find travel with id: " + id + ".";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
