package com.cars.comparison;

import com.cars.comparison.dto.car.CarDto;
import com.cars.comparison.entity.Car;
import com.cars.comparison.exceptions.ServiceException;
import com.cars.comparison.mapper.CarMapper;
import com.cars.comparison.repository.CarRepository;
import com.cars.comparison.serivces.cars.CarService;
import com.cars.comparison.serivces.cars.CarSimilarityCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarComparisonApplicationTests {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarSimilarityCalculator carSimilarityCalculator;

    private CarService carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository, carMapper, carSimilarityCalculator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"bmw_car.json", "audi_car.json"})
    public void testAddCar_Success(String fileName) {
        CarDto carDto = loadCarDtoFromJson("src/test/resources/" + fileName);
        Car car = loadCarEntityFromJson("src/test/resources/" + fileName.replace("_car.json", "_car_entity.json")); // Dummy Car object

        when(carMapper.toEntity(ArgumentMatchers.any(CarDto.class))).thenReturn(car);
        when(carRepository.save(ArgumentMatchers.any(Car.class))).thenReturn(car);

        boolean result = carService.addCar(carDto);

        assertTrue(result);

        verify(carMapper, times(1)).toEntity(carDto);
        verify(carRepository, times(1)).save(car);
    }

    @ParameterizedTest
    @ValueSource(strings = {"audi_car.json"})
    public void testGetCar_Success(String fileName) {

        CarDto carDto = loadCarDtoFromJson("src/test/resources/" + fileName);
        Car car = loadCarEntityFromJson("src/test/resources/" + fileName.replace("_car.json", "_car_entity.json"));
        when(carRepository.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(car));
        when(carMapper.toDto(ArgumentMatchers.any(Car.class))).thenReturn(carDto);

        CarDto result = carService.getCar(car.getId());

        assertNotNull(result);
    }

    @Test
    public void testGetCar_Exception() {
        when(carRepository.findById(ArgumentMatchers.anyLong())).thenThrow(new ServiceException(""));

        assertThrows(ServiceException.class, () -> carService.getCar(1L));
    }

    @Test
    public void testGetCar_NotFound() {
        when(carRepository.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.empty());

        CarDto result = carService.getCar(1L);

        assertNull(result);
    }

    private CarDto loadCarDtoFromJson(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filename), CarDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Car loadCarEntityFromJson(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filename), Car.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
