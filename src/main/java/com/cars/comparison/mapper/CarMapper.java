package com.cars.comparison.mapper;

import com.cars.comparison.dto.car.CarDto;
import com.cars.comparison.dto.car.SpecificationsDto;
import com.cars.comparison.entity.Car;
import com.cars.comparison.entity.Specifications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car toEntity(CarDto carDto);

    CarDto toDto(Car car);

    @Mapping(target = "car", ignore = true)
    Specifications toEntity(SpecificationsDto specificationsDto);

    default Long mapCarId(CarDto carDto) {
        return carDto.getId(); // Map the carId from CarDto to SpecificationsDto
    }

    List<CarDto> toDtoList(List<Car> cars);
}


