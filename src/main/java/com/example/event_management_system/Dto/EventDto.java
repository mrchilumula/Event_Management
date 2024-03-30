package com.example.event_management_system.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Setter
@Getter
public class EventDto {
    private String eventName;
    private String cityName;
    private LocalDate date;
    private String weather;
    private String distance;

    public EventDto(String eventName, String cityName, LocalDate date, String weather, String distance) {
        this.eventName = eventName;
        this.cityName = cityName;
        this.date = date;
        this.weather = weather;
        this.distance = distance;
    }

}