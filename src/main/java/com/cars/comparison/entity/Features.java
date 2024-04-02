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
@Table(name = "features", indexes = {
        @Index(name = "idx_feature_car_id", columnList = "car_id")
})
public class Features extends BaseEntity{

    // Safety features
    @Column(name = "airbags")
    private boolean airbags;

    @Column(name = "overspeed_warning")
    private boolean overspeedWarning;

    @Column(name = "three_point_seat_belts")
    private boolean threePointSeatBelts;

    @Column(name = "child_seat_anchors")
    private boolean childSeatAnchors;

    @Column(name = "seat_belt_warning")
    private boolean seatBeltWarning;

    // Braking features
    @Column(name = "abs")
    private boolean abs;

    @Column(name = "ebd")
    private boolean ebd;

    @Column(name = "braking_assist")
    private boolean brakingAssist;

    @Column(name = "electronic_stability_program")
    private boolean electronicStabilityProgram;

    @Column(name = "hill_hold")
    private boolean hillHold;

    @Column(name = "traction_control_system")
    private boolean tractionControlSystem;

    // Security features
    @Column(name = "central_locking")
    private boolean centralLocking;

    @Column(name = "engine_immobilizer")
    private boolean engineImmobilizer;

    @Column(name = "speed_sensing_door_lock")
    private boolean speedSensingDoorLock;

    @Column(name = "child_safety_lock")
    private boolean childSafetyLock;

    // Comfort features
    @Column(name = "ac")
    private boolean ac;

    @Column(name = "heater")
    private boolean heater;

    @Column(name = "sun_visors")
    private boolean sunVisors;

    @Column(name = "anti_glare_mirrors")
    private boolean antiGlareMirrors;

    @Column(name = "power_adjustable_seats")
    private boolean powerAdjustableSeats;

    @Column(name = "cooled_cabinet")
    private boolean cooledCabinet;

    @Column(name = "power_outlets")
    private boolean powerOutlets;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}
