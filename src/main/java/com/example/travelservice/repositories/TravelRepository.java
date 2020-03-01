package com.example.travelservice.repositories;

import com.example.travelservice.entities.Travel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Long> {
    List<Travel> findAllByOrderByAddedOnDesc();
    List<Travel> findByUserIdOrderByAddedOnDesc(UUID userId);
    boolean existsByUserIdAndOriginAndDestinationAndDate(UUID userId, String origin, String destination, Date date);
}
