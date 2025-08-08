package com.weather.repository;

import com.weather.model.PincodeLocation;
import com.weather.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    Optional<WeatherInfo> findByPincodeLocationAndDate(PincodeLocation pincodeLocation, LocalDate date);
}

