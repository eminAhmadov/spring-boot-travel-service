package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.repositories.TravelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return travelRepository.findAllByOrderByAddedOnDesc();
    }

    @Override
    public List<Travel> getAllForUserWithUserId(UUID userId) {
        return travelRepository.findByUserIdOrderByAddedOnDesc(userId);
    }

    @Override
    public ResponseEntity<Travel> createTravel(Travel travel) {
        boolean alreadyExists = travelRepository.existsByUserIdAndOriginAndDestinationAndDate(
                travel.getUserId(),
                travel.getOrigin(),
                travel.getDestination(),
                travel.getDate()
        );

        if (alreadyExists) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Travel savedTravel = travelRepository.save(travel);

        return new ResponseEntity<>(savedTravel, HttpStatus.OK);
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

    @Override
    public ResponseEntity<String> editTravel(Long id, Travel travel) {
        String message;

        boolean alreadyExists = travelRepository.existsByUserIdAndOriginAndDestinationAndDate(
                travel.getUserId(),
                travel.getOrigin(),
                travel.getDestination(),
                travel.getDate()
        );

        if (alreadyExists) {
            message = "Such travel already exists!";
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        Optional<Travel> foundTravel = travelRepository.findById(id);

        if (foundTravel.isPresent()) {
            travel.setId(id);
            travelRepository.save(travel);
            message = "Successfully updated travel with id: " + travel.getId() + ".";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        message = "Could not find travel with id: " + id + ".";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
