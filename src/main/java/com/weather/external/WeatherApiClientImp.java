package com.weather.external;

import com.weather.external.WeatherApiClient;
import com.weather.dto.WeatherApiResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClientImp implements WeatherApiClient {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherApiResponseDTO getWeather(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat +
                "&lon=" + lon + "&appid=" + apiKey + "&units=metric";

        return restTemplate.getForObject(url, WeatherApiResponseDTO.class);
    }
}

