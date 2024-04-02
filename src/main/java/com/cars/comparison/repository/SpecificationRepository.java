package com.cars.comparison.repository;

import com.cars.comparison.entity.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specifications, Long> {
}
