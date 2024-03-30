package com.example.event_management_system.Expections;

public class DistanceCalculationExpection extends RuntimeException {
    public DistanceCalculationExpection(String message) {
        super(message);
    }

    public DistanceCalculationExpection(String message, Throwable cause) {
        super(message, cause);
    }
}
