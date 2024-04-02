package com.cars.comparison.repository;

import com.cars.comparison.entity.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Features, Long> {
}
