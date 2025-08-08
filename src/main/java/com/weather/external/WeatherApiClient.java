package com.weather.external;

import com.weather.dto.WeatherApiResponseDTO;

public interface WeatherApiClient {
    WeatherApiResponseDTO getWeather(double lat, double lon);
}

