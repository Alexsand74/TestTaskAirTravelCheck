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
        segments = segs;
    }

    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}