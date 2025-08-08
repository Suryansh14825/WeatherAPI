package com.weather.repository;

import com.weather.model.PincodeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PincodeLocationRepository extends JpaRepository<PincodeLocation, Long> {
    Optional<PincodeLocation> findByPincode(String pincode);
}

