package com.cars.comparison.dto.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "\\d+", message = "Price must be numeric")
    private String price;

    @Valid
    @NotNull(message = "Specifications are required")
    private SpecificationsDto specifications;

    @Valid
    @NotNull(message = "Features are required")
    private FeaturesDto features;

}
