package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightCheck {
    public static List<Flight> flightValidation(List<Flight> arrayFlight){
        if(arrayFlight == null){
            throw new IllegalArgumentException(
                    //массив имеет значение null
                    "the array has a value null");
        }

         List resultFlight = arrayFlight.stream()
                .filter(flight -> segmentListValidation(flight.getSegments()) == true)
                .collect(Collectors.toList());

//        for (Flight f: arrayFlight) {
//            if (segmentListValidation( f.getSegments())){
//                resultFlight.add(f);
//            }
//        }
        return resultFlight;
    }
     private static boolean segmentListValidation ( List <Segment> flight){
         int counter = 0;
         for (Segment s: flight) {
            if(!segmentOneValidation(s)){ counter++;}
         }
         if (counter > 0){
              return false;
         }
        return true;
     }
     private static boolean segmentOneValidation (Segment seg){
        LocalDateTime thisDay = LocalDateTime.now();
        return (seg.getArrivalDate().isAfter(thisDay)
                && seg.getDepartureDate().isAfter(thisDay)
                && (seg.getArrivalDate().isAfter(seg.getDepartureDate())));

    }
}
