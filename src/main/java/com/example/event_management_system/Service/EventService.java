package com.example.event_management_system.Service;

import com.example.event_management_system.Dto.EventDto;
import com.example.event_management_system.Event;
import com.example.event_management_system.Expections.DistanceCalculationExpection;
import com.example.event_management_system.Expections.WeatherServiceException;
import com.example.event_management_system.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;
    @Autowired
    WeatherService weatherService;
    @Autowired
    DistanceCalculationService distanceService;

    public void addEvent(Event event)
    {
        eventRepo.save(event);
        // Add event to the database
    }

//    public List<EventDto> findEvents(String userLatitude, String userLongitude, LocalDate date) {
//        LocalDate endDate = date.plusDays(14);
//        List<Event> events = eventRepo.findByDateBetween(date, endDate);
//      //  System.out.println("Events: " + events.size());
//        List<EventDto> eventDtos = new ArrayList<>();
//        for (Event event : events) {
//            try {
//              //  System.out.println("Event: " + event.getEventName());
//              //  System.out.println("City: " + event.getCityName());
//                String distance = distanceService.calculateDistance(userLatitude, userLongitude, event.getLatitude(), event.getLongitude());
//               // System.out.println("Distance: " + distance);
//                String city = event.getCityName();
//                LocalDate eventDate = event.getDate();
//                String weather = weatherService.getWeather(city, eventDate);
//             //   System.out.println("Weather: " + weather);
//                eventDtos.add(new EventDto(event.getEventName(), event.getCityName(), event.getDate(), weather, distance));
//            } catch (DistanceCalculationExpection e) {
//                // Handle DistanceCalculationException
//                System.err.println("Error calculating distance for event: " + event.getEventName() + ", " + e.getMessage());
//            } catch (WeatherServiceException e) {
//                // Handle WeatherServiceException
//                System.err.println("Error fetching weather for event: " + event.getEventName() + ", " + e.getMessage());
//            } catch (Exception e) {
//                // Handle any other unexpected exception
//                System.err.println("An unexpected error occurred for event: " + event.getEventName() + ", " + e.getMessage());
//            }
//        }
//        eventDtos.sort(Comparator.comparing(EventDto::getDate));
//        return eventDtos;


public List<EventDto> findEvents(String userLatitude, String userLongitude, LocalDate date) {
    LocalDate endDate = date.plusDays(14);
    int pageSize = 100; // Adjust the batch size as needed

    Page<Event> pageResult = eventRepo.findByDateBetween(date, endDate, PageRequest.of(0, pageSize));
    List<Event> events = new ArrayList<>(pageResult.getContent());
    int totalPages = pageResult.getTotalPages();

    for (int page = 1; page < totalPages; page++) {
        Page<Event> nextPageResult = eventRepo.findByDateBetween(date, endDate, PageRequest.of(page, pageSize));
        events.addAll(nextPageResult.getContent());
    }

    List<EventDto> eventDtos = events.parallelStream()
            .map(event -> {
                try {
                    String distance = distanceService.calculateDistance(userLatitude, userLongitude, event.getLatitude(), event.getLongitude());
                    String weather = weatherService.getWeather(event.getCityName(), event.getDate());
                    return new EventDto(event.getEventName(), event.getCityName(), event.getDate(), weather, distance);
                } catch (DistanceCalculationExpection e) {
                    System.err.println("Error calculating distance for event: " + event.getEventName() + ", " + e.getMessage());
                } catch (WeatherServiceException e) {
                    System.err.println("Error fetching weather for event: " + event.getEventName() + ", " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred for event: " + event.getEventName() + ", " + e.getMessage());
                }
                return null;
            })
            .filter(dto -> dto != null)
            .toList();

    return eventDtos;

}
}





