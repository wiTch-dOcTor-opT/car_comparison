package com.cars.comparison.repository;

import com.cars.comparison.entity.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c " +
            "JOIN c.specifications s " +
            "WHERE c.id <> :specificCarId " +
            "ORDER BY SQRT(POWER(s.maxPower - :specificCarMaxPower, 2) + " +
            "               POWER(s.maxTorque - :specificCarMaxTorque, 2) + " +
            "               POWER(c.price - :specificCarPrice, 2)) ASC")
    List<Car> findSimilarCars(
            @Param("specificCarId") Long specificCarId,
            @Param("specificCarMaxPower") int specificCarMaxPower,
            @Param("specificCarMaxTorque") int specificCarMaxTorque,
            @Param("specificCarPrice") int specificCarPrice,
            Pageable pageable);
}
