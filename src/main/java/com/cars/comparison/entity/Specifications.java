package com.cars.comparison.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specifications", indexes = {
        @Index(name = "idx_spec_car_id", columnList = "car_id")
})
public class Specifications extends BaseEntity{

    // Engine specifications
    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "engine")
    private String engine;

    @Column(name = "max_power")
    private int maxPower;

    @Column(name = "max_torque")
    private int maxTorque;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "drivetrain")
    private String drivetrain;

    // Capacity specifications
    @Column(name = "doors")
    private int doors;

    @Column(name = "seating_capacity")
    private int seatingCapacity;

    @Column(name = "boot_space")
    private String bootSpace;

    @Column(name = "fuel_tank_capacity")
    private String fuelTankCapacity;

    // Dimensions
    @Column(name = "length")
    private String length;

    @Column(name = "breadth")
    private String breadth;

    @Column(name = "height")
    private String height;

    @Column(name = "wheelbase")
    private String wheelbase;

    // Miscellaneous
    @Column(name = "front_suspension")
    private String frontSuspension;

    @Column(name = "rear_suspension")
    private String rearSuspension;

    @Column(name = "steering_radius")
    private String steeringRadius;

    @Column(name = "front_tyre_size")
    private String frontTyreSize;

    @Column(name = "rear_tyre_size")
    private String rearTyreSize;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}
