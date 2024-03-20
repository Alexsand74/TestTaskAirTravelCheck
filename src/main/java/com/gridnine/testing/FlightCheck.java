package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class FlightCheck {
    public static List<Flight> departureBeforeCurrentTime(List<Flight> arrayFlight) {
        if (arrayFlight == null) {
            throw new IllegalArgumentException(
                    //массив имеет значение null
                    "the array has a value null");
        }
        return arrayFlight.stream()
                .filter(fil -> (fil.getSegments().stream()
                        .anyMatch((seg) -> validationTheSegmentForExpiredTime(seg))) == true)
                .collect(Collectors.toList());
    }

    private static boolean validationTheSegmentForExpiredTime(Segment seg) {
        LocalDateTime thisDay = LocalDateTime.now();
        return (seg.getArrivalDate().isAfter(thisDay)
                && seg.getDepartureDate().isAfter(thisDay));
    }

    public static List<Flight> arrivalDateIsEarlierThanDepartureDate(List<Flight> arrayFlight) {
        if (arrayFlight == null) {
            throw new IllegalArgumentException(
                    //массив имеет значение null
                    "the array has a value null");
        }
        return arrayFlight.stream()
                .filter(fil -> (fil.getSegments().stream()
                        .anyMatch((seg) -> validationSegmentCorrespondenceBetweenDepartureAndArrival(seg)))
                        == true)
                .collect(Collectors.toList());
    }

    private static boolean validationSegmentCorrespondenceBetweenDepartureAndArrival(Segment seg) {
        LocalDateTime thisDay = LocalDateTime.now();
        return (seg.getArrivalDate().isAfter(seg.getDepartureDate()));
    }

    public static List<Flight> waitingOnTheGroundForMoreThanTwoHours(List<Flight> arrayFlight) {
        if (arrayFlight == null) {
            throw new IllegalArgumentException(
                    //массив имеет значение null
                    "the array has a value null");
        }
        return arrayFlight.stream()
                .filter(flight -> segmentListValidationMoreThanTwoHours(flight.getSegments()) == true)
                .collect(Collectors.toList());
    }

    private static boolean segmentListValidationMoreThanTwoHours(List<Segment> flight) {
        if (flight.size() > 1) {
            long sumOfHours = 0;
            for (int i = flight.size() - 1; i > 0; i--) {
//              Дата вылета ---> LocalDateTime departureDate;
//              Дата прибытия --->  LocalDateTime arrivalDate;
                LocalDateTime dataMax = flight.get(i).getDepartureDate();
                Long endSessionSeconds = dataMax.toEpochSecond(ZoneOffset.UTC);
                LocalDateTime dataMin = flight.get(i - 1).getArrivalDate();
                Long startSessionSeconds = dataMin.toEpochSecond(ZoneOffset.UTC);
                sumOfHours += (endSessionSeconds - startSessionSeconds);
            }
//            System.out.println(" 7200 =?--> " + sumOfHours);
//            System.out.println();
            return 2 * 60 * 60 > sumOfHours;
        }
        return true;
    }

    //======================================================================================================
    public static List<Flight> flightValidation(List<Flight> arrayFlight) {
        if (arrayFlight == null) {
            throw new IllegalArgumentException(
                    //массив имеет значение null
                    "the array has a value null");
        }

        List resultFlight = arrayFlight.stream()
                .filter(flight -> segmentListValidation(flight.getSegments()) == true)
                .collect(Collectors.toList());
/**
 *        for (Flight f: arrayFlight) {
 *        if (segmentListValidation( f.getSegments())){
 *                resultFlight.add(f);
 *            }
 *        }
 */
        return resultFlight;
    }

    private static boolean segmentListValidation(List<Segment> flight) {

//         int counter = 0;
//         for (Segment s: flight) {
//            if(!segmentOneValidation(s)){ counter++;}
//         }
//          if (counter > 0){
//              return false;
//          }
//       return true;

        if (flight.size() > 1) {
            long sumOfHours = 0;
            for (int i = flight.size() - 1; i > 0; i--) {
//              Дата вылета ---> LocalDateTime departureDate;
//              Дата прибытия --->  LocalDateTime arrivalDate;
                LocalDateTime dataMax = flight.get(i).getArrivalDate();
                Long endSessionSeconds = dataMax.toEpochSecond(ZoneOffset.UTC);
                LocalDateTime dataMin = flight.get(i - 1).getDepartureDate();
                Long startSessionSeconds = dataMin.toEpochSecond(ZoneOffset.UTC);
                sumOfHours += (endSessionSeconds - startSessionSeconds);
            }
            System.out.println(" 7200 = " + sumOfHours);
            return 2 * 60 * 60 < sumOfHours;
        }

        return flight.stream()
                .anyMatch((f) -> segmentOneValidation(f));
    }

    private static boolean segmentOneValidation(Segment seg) {
        LocalDateTime thisDay = LocalDateTime.now();
        return (seg.getArrivalDate().isAfter(thisDay)
                && seg.getDepartureDate().isAfter(thisDay)
                && (seg.getArrivalDate().isAfter(seg.getDepartureDate())));

    }
}
