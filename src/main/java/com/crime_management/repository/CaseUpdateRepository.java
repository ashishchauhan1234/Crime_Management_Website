package com.crime_management.repository;

import com.crime_management.entity.CaseUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseUpdateRepository extends JpaRepository<CaseUpdate, Long> {}
