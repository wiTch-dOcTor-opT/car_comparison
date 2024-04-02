package com.cars.comparison.controller;

import com.cars.comparison.dto.car.CarDto;
import com.cars.comparison.serivces.cars.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/{car_id}")
    public ResponseEntity getCar(@PathVariable("car_id") String carId){
        CarDto cars = carService.getCar(Long.parseLong(carId));
        if(cars != null){
            return ResponseEntity.status(HttpStatus.OK).body(cars);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No similar cars found");
    }

    @GetMapping("/{car_id}/similar")
    public ResponseEntity getSimilarCars(@PathVariable("car_id") String carId){
        List<CarDto> cars = carService.getSimilarCars(Long.parseLong(carId));
        if(cars != null){
            return ResponseEntity.status(HttpStatus.OK).body(cars);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No similar cars found");
    }

    @PostMapping("/add")
    public ResponseEntity addCar(@RequestBody @Valid CarDto carDto) {
        if(carService.addCar(carDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Car info is saved");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Car info is not saved");
    }

    @GetMapping("/{firstCarId}/compare/{secondCarId}")
    public String getComparison(@PathVariable Long firstCarId, @PathVariable Long secondCarId) {
        return carService.getComparisonTable(firstCarId, secondCarId, false);
    }

    @GetMapping("/{firstCarId}/compare/{secondCarId}/differences")
    public String getDifferences(@PathVariable Long firstCarId, @PathVariable Long secondCarId) {
        return carService.getComparisonTable(firstCarId, secondCarId, true);
    }

}
