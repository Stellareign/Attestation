package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    Segment segment1 = new Segment(LocalDateTime.now().plusDays(2),
            LocalDateTime.now().plusDays(2).plusHours(4));
    Segment segment2 = new Segment(LocalDateTime.now().plusDays(2).plusHours(5),
            LocalDateTime.now().plusDays(2).plusHours(7));

    Segment segment3 = new Segment(LocalDateTime.now().minusDays(1).plusHours(1),
            LocalDateTime.now().plusDays(2).plusHours(2));
    Segment segment4 = new Segment(LocalDateTime.now().plusHours(1),
            LocalDateTime.now().minusDays(2).plusHours(2));

    @org.junit.jupiter.api.Test
    @DisplayName(value = "Вывод в консоль списка всех перелётов")
    void allFlightsList() {
        List<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(segment1));
        flights.add(flight1);
        flights.add(flight2);

//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));

//        allFlightsList(flights);

        String expectedOutput = flight1.toString() + System.lineSeparator() + flight2.toString() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());

    }

    @org.junit.jupiter.api.Test
    void flightsWithoutFlightDepartingInThePast() {
    }

    @org.junit.jupiter.api.Test
    void flightsWithoutFlightWereArrivalBeforeDeparture() {
    }

    @org.junit.jupiter.api.Test
    void flightsWithoutFlightWereTransferMoreThanTwoHours() {
    }


}