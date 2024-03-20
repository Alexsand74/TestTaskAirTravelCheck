package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightListCreated = FlightBuilder.createFlights();
        System.out.println(flightListCreated);
        System.out.println();
        List<Flight> flightListFiltered = FlightCheck.waitingOnTheGroundForMoreThanTwoHours(flightListCreated);
        System.out.println(flightListFiltered);
        System.out.println();
        List<Flight> flightListFiltered1 = FlightCheck.arrivalDateIsEarlierThanDepartureDate(flightListCreated);
        System.out.println(flightListFiltered1);
        System.out.println();
        List<Flight> flightListFiltered2 = FlightCheck.departureBeforeCurrentTime(flightListCreated);
        System.out.println(flightListFiltered2);
        System.out.println();
        List<Flight> flightListFilteredAll = FlightCheck.waitingOnTheGroundForMoreThanTwoHours(
                                              FlightCheck.arrivalDateIsEarlierThanDepartureDate(
                                               FlightCheck.departureBeforeCurrentTime(flightListCreated)));
        System.out.println(flightListFilteredAll);
    }
}