package com.crime_management.repository;

import com.crime_management.entity.CrimeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeLocationRepository extends JpaRepository<CrimeLocation, Long> {}
