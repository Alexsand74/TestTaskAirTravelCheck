package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.FlightBuilder.createFlight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightCheckTest {
    LocalDateTime threeDaysFromNow = null;
    List<Flight> result = null;
    List<Flight> expected = null;

    @BeforeEach
    void creatingTestData() {
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
        result = FlightBuilder.createFlights();
        expected = Arrays.asList(
                //A normal flight with two hour duration
                //0 - Обычный полет продолжительностью два часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                //1 - Обычный многосегментный полет-рейс
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(1),
                        threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past
                //2 - Рейс, вылетающий в прошлом
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives
                //3 - Рейс, который вылетает до прибытия
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time
                //4 - Рейс продолжительностью более двух часов наземного времени
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                //5 - Еще один рейс с наземным временем более двух часов.
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(7), threeDaysFromNow.plusHours(8)));
    }

    @Test
    void testDepartureEarlierThanCurrentTime() {
        var actual = FlightCheck.waitingOnTheGroundForMoreThanTwoHours(expected);
        List<Flight> expectedLocal = new ArrayList<>(expected);
        expectedLocal.remove(5);
        expectedLocal.remove(4);

        assertEquals(expectedLocal.size(), actual.size());
        assertEquals(expectedLocal.toString(), actual.toString());
        assertEquals(expectedLocal, actual);
    }

    @Test
    void testArrivalDateIsEarlierThanDepartureDate() {
        var actual = FlightCheck.arrivalDateIsEarlierThanDepartureDate(expected);
        List<Flight> expectedLocal = new ArrayList<>(expected);
        expectedLocal.remove(3);

        assertEquals(expectedLocal.size(), actual.size());
        assertEquals(expectedLocal.toString(), actual.toString());
        assertEquals(expectedLocal, actual);
    }

    @Test
    void testDepartureBeforeCurrentTime() {
        var actual = FlightCheck.departureBeforeCurrentTime(expected);
        List<Flight> expectedLocal = new ArrayList<>(expected);
        expectedLocal.remove(2);

        assertEquals(expectedLocal.size(), actual.size());
        assertEquals(expectedLocal.toString(), actual.toString());
        assertEquals(expectedLocal, actual);
    }
}

