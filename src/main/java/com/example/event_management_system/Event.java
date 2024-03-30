package com.example.event_management_system;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Getter
@Setter
public class Event {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String eventName;
        private String cityName;
        private LocalDate date;
        private LocalTime time;
        private String latitude;
        private String longitude;


}
