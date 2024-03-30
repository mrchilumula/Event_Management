package com.example.event_management_system.Repo;

import com.example.event_management_system.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    public Event save(Event event);
    public List<Event> findByLatitudeBetweenAndLongitudeBetweenAndDateBetween(
           String minLatitude, String maxLatitude,
          String minLongitude,String maxLongitude,
            LocalDate startDate, LocalDate endDate
    );


    List<Event> findByDateBetween(LocalDate date, LocalDate endDate);
    Page<Event> findByDateBetween(LocalDate date, LocalDate endDate, Pageable pageable);
}
