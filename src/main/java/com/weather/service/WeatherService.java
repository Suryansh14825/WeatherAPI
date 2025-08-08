package com.weather.service;

import com.weather.dto.WeatherResponseDTO;

public interface WeatherService {
    WeatherResponseDTO getWeather(String pincode, String date);
}

