package com.gridnine.testing;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 * Бин, представляющий полет.
 */
public class Flight {
    private final List<Segment> segments;

    Flight(final List<Segment> segs) {
        segments = Objects.requireNonNull(segs);
    }

    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" <-> "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(segments, flight.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segments);
    }
}
