package com.crime_management.repository;

import com.crime_management.entity.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeTypeRepository extends JpaRepository<CrimeType, Long> {}
