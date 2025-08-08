package com.weather.controller;

import com.weather.dto.WeatherRequestDTO;
import com.weather.dto.WeatherResponseDTO;
import com.weather.model.PincodeLocation;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public ResponseEntity<WeatherResponseDTO> getWeather(@RequestBody WeatherRequestDTO request) {
        WeatherResponseDTO response = weatherService.getWeather(request.getPincode(), request.getForDate());
        return ResponseEntity.ok(response);
    }
}

