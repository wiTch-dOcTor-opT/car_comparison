package com.cars.comparison.serivces.cars;

import com.cars.comparison.dto.car.CarDto;
import com.cars.comparison.exceptions.DuplicateEntityException;
import com.cars.comparison.exceptions.ServiceException;
import com.cars.comparison.mapper.CarMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.cars.comparison.entity.Car;
import com.cars.comparison.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarSimilarityCalculator carSimilarityCalculator;

    @Transactional
    public boolean addCar(CarDto carDto) {
        try {
            Car car = carMapper.toEntity(carDto);
            car.getSpecifications().setCar(car);
            car.getFeatures().setCar(car);
            Car savedCar = carRepository.save(car);
            return savedCar != null;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException("Car with the given name already exists", e);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding the car", e);
        }
    }

    @Transactional(readOnly = true)
    public CarDto getCar(Long carId) {
        try {
            Car car = carRepository.findById(carId).orElse(null);
            return carMapper.toDto(car);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving the car", e);
        }
    }

    @Transactional(readOnly = true)
    public List<CarDto> getSimilarCars(Long carId) {
        try {
            Car car = carRepository.getById(carId);
            List<Car> carsList = carRepository.findSimilarCars(carId, car.getSpecifications().getMaxPower(),
                    car.getSpecifications().getMaxTorque(), car.getPrice(), PageRequest.of(0, 30));
            return carMapper.toDtoList(carSimilarityCalculator.findSimilarCars(carsList, car, 3));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while retrieving similar cars", e);
        }
    }

    public String getComparisonTable(Long firstCarId, Long secondCarId, boolean showOnlyDifference) {
        CarDto firstCar = carMapper.toDto(carRepository.findById(firstCarId).orElse(null));
        CarDto secondCar = carMapper.toDto(carRepository.findById(secondCarId).orElse(null));

        if (firstCar == null) {
            return HtmlGenerator.generateErrorMessage("Car with ID " + firstCarId + " not found.");
        }

        if (secondCar == null) {
            return HtmlGenerator.generateErrorMessage("Car with ID " + secondCarId + " not found.");
        }

        List<String> carAttributes = getAllAttributes(firstCar);
        List<String> firstCarValues = getAttributeValues(firstCar, carAttributes);
        List<String> secondCarValues = getAttributeValues(secondCar, carAttributes);

        List<String> specificationsAttributes = getAllAttributes(firstCar.getSpecifications());
        List<String> specificationsFirstCarValues = getAttributeValues(firstCar.getSpecifications(), specificationsAttributes);
        List<String> specificationsSecondCarValues = getAttributeValues(secondCar.getSpecifications(), specificationsAttributes);

        List<String> featuresAttributes = getAllAttributes(firstCar.getFeatures());
        List<String> featuresFirstCarValues = getAttributeValues(firstCar.getFeatures(), featuresAttributes);
        List<String> featuresSecondCarValues = getAttributeValues(secondCar.getFeatures(), featuresAttributes);

        if(showOnlyDifference){
            return HtmlGenerator.generateDifferencesTable(
                    firstCar.getName(), secondCar.getName(),
                    carAttributes, firstCarValues, secondCarValues,
                    specificationsAttributes, specificationsFirstCarValues, specificationsSecondCarValues,
                    featuresAttributes, featuresFirstCarValues, featuresSecondCarValues
            );
        }else{
            return HtmlGenerator.generateComparisonTable(
                    firstCar.getName(), secondCar.getName(),
                    carAttributes, firstCarValues, secondCarValues,
                    specificationsAttributes, specificationsFirstCarValues, specificationsSecondCarValues,
                    featuresAttributes, featuresFirstCarValues, featuresSecondCarValues
            );
        }
    }

    private List<String> getAllAttributes(Object object) {
        List<String> attributes = new ArrayList<>();
        if (object != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (object instanceof CarDto &&
                        (field.getName().equals("specifications") || field.getName().equals("features"))) {
                    continue;
                }
                attributes.add(field.getName());
            }
        }
        return attributes;
    }

    private List<String> getAttributeValues(Object object, List<String> attributes) {
        List<String> values = new ArrayList<>();
        if (object != null) {
            for (String attribute : attributes) {
                try {
                    Field field = object.getClass().getDeclaredField(attribute);
                    field.setAccessible(true);
                    Object value = field.get(object);
                    values.add(value != null ? value.toString() : "");
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    values.add("");
                }
            }
        } else {
            values.addAll(Arrays.asList(new String[attributes.size()]));
        }
        return values;
    }
}
