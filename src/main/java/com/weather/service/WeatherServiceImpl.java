package com.weather.service;

import com.weather.dto.WeatherResponseDTO;
import com.weather.external.WeatherApiClient;
import com.weather.model.PincodeLocation;
import com.weather.model.WeatherInfo;
import com.weather.repository.PincodeLocationRepository;
import com.weather.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private PincodeLocationRepository pincodeRepo;

    @Autowired
    private WeatherInfoRepository weatherRepo;

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Override
    public WeatherResponseDTO getWeather(String pincode, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);

        // Step 1: Get or Save PincodeLocation
        PincodeLocation location = pincodeRepo.findByPincode(pincode)
                .orElseGet(() -> {
                    // Assume hardcoded values (for now, or use Geocoding API)
                    PincodeLocation newLocation = new PincodeLocation();
                    newLocation.setPincode(pincode);
                    newLocation.setLatitude(18.5204);  // dummy values
                    newLocation.setLongitude(73.8567);
                    return pincodeRepo.save(newLocation);
                });

        // Step 2: Check if WeatherInfo already exists
        Optional<WeatherInfo> existingWeather =
                weatherRepo.findByPincodeLocationAndDate(location, date);

        WeatherInfo info;

        if (existingWeather.isPresent()) {
            info = existingWeather.get();
        } else {
            // Step 3: Call API
            WeatherApiResponseDTO apiResponse = weatherApiClient
                    .getWeather(location.getLatitude(), location.getLongitude());

            info = new WeatherInfo();
            info.setPincodeLocation(location);
            info.setDate(date);
            info.setTemperature(apiResponse.getMain().getTemp());
            info.setDescription(apiResponse.getWeather().get(0).getDescription());

            weatherRepo.save(info);
        }

        // Step 4: Prepare Response
        WeatherResponseDTO response = new WeatherResponseDTO();
        response.setPincode(pincode);
        response.setCity("Pune"); // Optional: can use Geocoding API
        response.setDate(date.toString());
        response.setTemperature(info.getTemperature());
        response.setDescription(info.getDescription());

        return response;
    }
}

