package com.example.travelservice.services;

import com.example.travelservice.entities.Travel;
import com.example.travelservice.repositories.TravelRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Travel> getAll(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "addedOn"));
        return travelRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Travel> getAllForUserWithUserId(UUID userId, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "addedOn"));
        return travelRepository.findByUserId(userId, pageable).getContent();
    }

    @Override
    public List<Travel> search(String origin, String destination, Date fromDate, Date toDate, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "addedOn"));
        if (fromDate == null) {
            if (toDate == null) {
                return travelRepository.findByOriginAndDestination(origin, destination, pageable).getContent();
            } else {
                return travelRepository.findByOriginAndDestinationAndDateBefore(origin, destination, toDate, pageable).getContent();
            }
        } else {
            if (toDate == null) {
                return travelRepository.findByOriginAndDestinationAndDateAfter(origin, destination, fromDate, pageable).getContent();
            } else {
                return travelRepository.findByOriginAndDestinationAndDateBetween(origin, destination, fromDate, toDate, pageable).getContent();
            }
        }
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

        this.triggerNotificationAlerts(travel.getName(), travel.getOrigin(), travel.getDestination(), travel.getDate());
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

    private void triggerNotificationAlerts(String travelerName, String travelOrigin, String travelDestination, Date travelDate) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8090/notificationAlert/trigger";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String requestJson = "{" +
                "\"travelerName\":\"" + travelerName + "\",\n" +
                "\"travelOrigin\":\"" + travelOrigin + "\",\n" +
                "\"travelDestination\":\"" + travelDestination + "\",\n" +
                "\"travelDate\":\"" + dateFormat.format(travelDate) + "\"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);
    }
}
