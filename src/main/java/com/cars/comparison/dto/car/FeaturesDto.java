package com.cars.comparison.dto.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeaturesDto {

    @NotNull(message = "Airbags field is required")
    private boolean airbags;

    @NotNull(message = "Overspeed Warning field is required")
    private boolean overspeedWarning;

    @NotNull(message = "Three Point Seat Belts field is required")
    private boolean threePointSeatBelts;

    @NotNull(message = "Child Seat Anchors field is required")
    private boolean childSeatAnchors;

    @NotNull(message = "Seat Belt Warning field is required")
    private boolean seatBeltWarning;

    @NotNull(message = "ABS field is required")
    private boolean abs;

    @NotNull(message = "EBD field is required")
    private boolean ebd;

    @NotNull(message = "Braking Assist field is required")
    private boolean brakingAssist;

    @NotNull(message = "Electronic Stability Program field is required")
    private boolean electronicStabilityProgram;

    @NotNull(message = "Hill Hold field is required")
    private boolean hillHold;

    @NotNull(message = "Traction Control System field is required")
    private boolean tractionControlSystem;

    @NotNull(message = "Central Locking field is required")
    private boolean centralLocking;

    @NotNull(message = "Engine Immobilizer field is required")
    private boolean engineImmobilizer;

    @NotNull(message = "Speed Sensing Door Lock field is required")
    private boolean speedSensingDoorLock;

    @NotNull(message = "Child Safety Lock field is required")
    private boolean childSafetyLock;

    @NotNull(message = "AC field is required")
    private boolean ac;

    @NotNull(message = "Heater field is required")
    private boolean heater;

    @NotNull(message = "Sun Visors field is required")
    private boolean sunVisors;

    @NotNull(message = "Anti Glare Mirrors field is required")
    private boolean antiGlareMirrors;

    @NotNull(message = "Power Adjustable Seats field is required")
    private boolean powerAdjustableSeats;

    @NotNull(message = "Cooled Cabinet field is required")
    private boolean cooledCabinet;

    @NotNull(message = "Power Outlets field is required")
    private boolean powerOutlets;
}
