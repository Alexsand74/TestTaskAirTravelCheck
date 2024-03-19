package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightListCreated = FlightBuilder.createFlights();
        System.out.println(flightListCreated);
        System.out.println();
        List<Flight> flightListFiltered = FlightCheck.flightValidation(flightListCreated);
        System.out.println(flightListFiltered);

    }
}