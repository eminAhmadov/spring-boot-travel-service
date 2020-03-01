package com.example.travelservice.repositories;

import com.example.travelservice.entities.Travel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends CrudRepository<Travel, Long> {
    public List<Travel> findAllByOrderByDateDesc();
}
