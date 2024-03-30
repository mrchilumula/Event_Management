package com.example.event_management_system.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service

public class DistanceCalculationService {

        @Value("${distance.api.url}")
        private String distanceApiUrl;

        @Value("${distance.api.key}")
        private String distanceApiKey;

        public String  calculateDistance(String latitude1, String longitude1, String latitude2, String longitude2) throws Exception {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                String apiUrl = distanceApiUrl + "?code=" + distanceApiKey +
                        "&latitude1=" + latitude1 + "&longitude1=" + longitude1 +
                        "&latitude2=" + latitude2 + "&longitude2=" + longitude2;
                HttpGet request = new HttpGet(apiUrl);
                String responseBody;
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    responseBody = EntityUtils.toString(response.getEntity());
                }
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> distanceResponse = mapper.readValue(responseBody, Map.class);
                return distanceResponse.get("distance");
            }
        }
}
