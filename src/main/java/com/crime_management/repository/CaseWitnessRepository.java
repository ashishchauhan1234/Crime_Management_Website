package com.crime_management.repository;

import com.crime_management.entity.CaseWitness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseWitnessRepository extends JpaRepository<CaseWitness, Long> {}
