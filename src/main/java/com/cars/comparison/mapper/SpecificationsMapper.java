package com.cars.comparison.mapper;


import com.cars.comparison.dto.car.SpecificationsDto;
import com.cars.comparison.entity.Specifications;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(builder = @Builder(disableBuilder = true))
public interface SpecificationsMapper {

    SpecificationsMapper INSTANCE = Mappers.getMapper(SpecificationsMapper.class);

    @Mapping(target = "id", ignore = true)
    Specifications toEntity(SpecificationsDto specificationsDto);

    SpecificationsDto toDto(Specifications specifications);
}
