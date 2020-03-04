package com.example.travelservice.repositories;

import com.example.travelservice.entities.Travel;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface TravelRepository extends PagingAndSortingRepository<Travel, Long> {
    Page<Travel> findAll(Pageable pageable);
    Page<Travel> findByUserId(UUID userId, Pageable pageable);
    boolean existsByUserIdAndOriginAndDestinationAndDate(UUID userId, String origin, String destination, Date date);
}
