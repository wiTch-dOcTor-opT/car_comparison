package com.cars.comparison.dto.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationsDto {

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Engine is required")
    private String engine;

    @NotBlank(message = "Max Power is required")
    @Pattern(regexp = "\\d+", message = "Price must be numeric")
    private int maxPower;

    @NotBlank(message = "Max Torque is required")
    @Pattern(regexp = "\\d+", message = "Price must be numeric")
    private int maxTorque;

    @NotBlank(message = "Mileage is required")
    private String mileage;

    @NotBlank(message = "Drivetrain is required")
    private String drivetrain;

    @NotBlank(message = "Capacity is required")
    private String capacity;

    @NotBlank(message = "Doors is required")
    private String doors;

    @NotBlank(message = "Seating Capacity is required")
    private String seatingCapacity;

    @NotBlank(message = "BootSpace is required")
    private String bootSpace;

    @NotBlank(message = "Fuel Tank Capacity is required")
    private String fuelTankCapacity;

    @NotBlank(message = "Dimensions is required")
    private String dimensions;

    @NotBlank(message = "Length is required")
    private String length;

    @NotBlank(message = "Breadth is required")
    private String breadth;

    @NotBlank(message = "Height is required")
    private String height;

    @NotBlank(message = "Wheelbase is required")
    private String wheelbase;

    @NotBlank(message = "Front Suspension is required")
    private String frontSuspension;

    @NotBlank(message = "Rear Suspension is required")
    private String rearSuspension;

    @NotBlank(message = "Steering Radius is required")
    private String steeringRadius;

    @NotBlank(message = "Front Tyre Size is required")
    private String frontTyreSize;

    @NotBlank(message = "Rear Tyre Size is required")
    private String rearTyreSize;
}
