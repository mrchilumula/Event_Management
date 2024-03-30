package com.example.event_management_system.Service;

import com.example.event_management_system.Expections.WeatherServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
@Service

public class WeatherService {
    @Autowired
    RestTemplate restTemplate ;

        @Value("${weather.api.url}")
        private String weatherApiUrl;

        @Value("${weather.api.key}")
        private String weatherApiKey;
    public String getWeather(String city, LocalDate date) {
        String apiUrl = weatherApiUrl + "?code=" + weatherApiKey + "&city=" + city + "&date=" + date.toString();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new WeatherServiceException("Weather API responded with status code: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new WeatherServiceException("Error calling Weather API: " + e.getMessage(), e);
        }
    }


}
