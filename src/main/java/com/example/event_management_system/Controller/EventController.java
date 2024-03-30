package com.example.event_management_system.Controller;

import com.example.event_management_system.Dto.EventDto;
import com.example.event_management_system.Event;
import com.example.event_management_system.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/events")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
        return new ResponseEntity<>("Event added successfully", HttpStatus.CREATED);
    }

//    @GetMapping("/weather")
//    public String getWeather(@RequestParam String city, @RequestParam String date) {
//        final String API_URL = "https://gg-backend-assignment.azurewebsites.net/api/Weather";
//        final String API_KEY = "KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg==";
//
//
//        // Build the URL with query parameters
//        String url = String.format("%s?code=%s&city=%s&date=%s", API_URL, API_KEY, city, date);
//
//        // Make the HTTP GET request and retrieve the response
//        return restTemplate.getForObject(url, String.class);
//    }
//    @GetMapping("/distance")
//    public String getDistance(
//            @RequestParam double latitude1,
//            @RequestParam double longitude1,
//            @RequestParam double latitude2,
//            @RequestParam double longitude2) {
//
//        final String API_URL = "https://gg-backend-assignment.azurewebsites.net/api/Distance";
//        final String API_KEY = "IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ==";
//
//
//        // Build the URL with query parameters
//        String url = String.format("%s?code=%s&latitude1=%f&longitude1=%f&latitude2=%f&longitude2=%f",
//                API_URL, API_KEY, latitude1, longitude1, latitude2, longitude2);
//
//        // Make the HTTP GET request and retrieve the response
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        // Check if the request was successful (status code 200)
//        if (response.getStatusCodeValue() == 200) {
//            return response.getBody();
//        } else {
//            return "Error: Unable to fetch distance data";
//        }
//    }
    @GetMapping("/events/find")
    public ResponseEntity<List<EventDto>> findEvents(@RequestParam String userLatitude,
                                                     @RequestParam String userLongitude,
                                                     @RequestParam String date) {
        try {
            LocalDate eventDate = LocalDate.parse(date);
            List<EventDto> events =  eventService.findEvents(userLatitude, userLongitude, eventDate);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}







