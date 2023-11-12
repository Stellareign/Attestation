package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
//*****************************************************************************************************************
    // обычный перелёт
   private final Segment segment1 = new Segment(LocalDateTime.now().plusDays(2),
            LocalDateTime.now().plusDays(2).plusHours(4));

    // обычный перелёт
    private final Segment segment2 = new Segment(LocalDateTime.now().plusDays(2).plusHours(6),
            LocalDateTime.now().plusDays(2).plusHours(7));

    // обычный перелёт
    private final Segment segment5 = new Segment(LocalDateTime.now().plusDays(2).plusHours(5),
            LocalDateTime.now().plusDays(2).plusHours(6));
    // обычный перелёт
    private final Segment segment6 = new Segment(LocalDateTime.now().plusDays(2).plusHours(7),
            LocalDateTime.now().plusDays(2).plusHours(8));

    // перелёт с просроченным вылетом
    private final Segment segment3 = new Segment(LocalDateTime.now().minusDays(1),
            LocalDateTime.now().plusDays(2).plusHours(2));

    // перелёт с прилётом раньше вылета
    private final Segment segment4 = new Segment(LocalDateTime.now().plusHours(1),
            LocalDateTime.now().minusDays(1));
//*******************************************************************************************************************
    @Test
    @DisplayName(value = "Вывод в консоль списка всех перелётов")
    void allFlightsListTest() {
        List<Flight> testFlights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(segment1));
        testFlights.add(flight1);
        testFlights.add(flight2);
        String headString = "\n *********************** Список всех перелётов  ****************************** \n";


        FlightService.allFlightsList(testFlights);

        String expectedOutput = System.lineSeparator() + flight1.toString() + System.lineSeparator() + flight2.toString()
                + System.lineSeparator();
        assertEquals(headString + expectedOutput, outputStreamCaptor.toString());
        }

    @Test
    @DisplayName(value = "Без перелёта с просроченным вылетом")
    void flightsWithoutFlightDepartingInThePastTest() {
        List<Flight> testFlights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(segment1, segment3));

        testFlights.add(flight1);
        testFlights.add(flight2);
        String  headString = "\n **************** Список без перелётов с вылетом в прошлом  ******************** \n";

        List<Flight> newTestFlights = FlightService.flightsWithoutFlightDepartingInThePast(testFlights);

        String expectedOutput = System.lineSeparator() + flight1.toString() + System.lineSeparator();

        assertEquals(1, newTestFlights.size());
        assertTrue(newTestFlights.contains(flight1));
        assertEquals( headString + expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    @DisplayName(value = "Без перелётов с прилётом раньше вылета")
    void flightsWithoutFlightWereArrivalBeforeDepartureTest() {
        List<Flight> testFlights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment4));
        Flight flight2 = new Flight(Arrays.asList(segment2, segment3));
        Flight flight3 = new Flight(Arrays.asList(segment1, segment2));

        testFlights.add(flight1);
        testFlights.add(flight2);
        testFlights.add(flight3);
        String headString = "\n **************** Список без перелётов с прилётом раньше вылета  ***************\n";

        List<Flight> newTestFlights = FlightService.flightsWithoutFlightWereArrivalBeforeDeparture(testFlights);

        String expectedOutput = System.lineSeparator() + flight2.toString() + System.lineSeparator()
                + flight3.toString() + System.lineSeparator();

        assertEquals(2, newTestFlights.size());
        assertTrue(newTestFlights.contains(flight2) && newTestFlights.contains(flight3));
        assertEquals(headString + expectedOutput, outputStreamCaptor.toString());
    }


    @Test
    @DisplayName(value = "С пересадкой < 2 часов ")
    void flightsWithoutFlightWereTransferMoreThanTwoHoursTest() {
        List<Flight> testFlights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(segment1, segment5));
        Flight flight3 = new Flight(Arrays.asList(segment5, segment6));
        Flight flight4 = new Flight(Arrays.asList(segment5));

        testFlights.add(flight1);
        testFlights.add(flight2);
        testFlights.add(flight3);
        testFlights.add(flight4);
        String headString = "\n ****** Список без перелётов, в которых общее время пересадок длится более 2-х часов  ********* \n";

        List<Flight> newTestFlights = FlightService.flightsWithoutFlightWereTransferMoreThanTwoHours(testFlights);

        String expectedOutput = System.lineSeparator() + flight2.toString() + System.lineSeparator()
                + flight3.toString() + System.lineSeparator();

        assertEquals(2, newTestFlights.size());
        assertTrue(newTestFlights.contains(flight2) && newTestFlights.contains(flight3));
        assertEquals(headString + expectedOutput, outputStreamCaptor.toString());
    }


    @Test
    @DisplayName(value = "Без пересадок")
    void flightsWithoutFlightsTransferWithoutTransfersTest() {
        List<Flight> testFlights = new ArrayList<>();

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(Arrays.asList(segment1, segment5));
        Flight flight3 = new Flight(Arrays.asList(segment5, segment6));
        Flight flight4 = new Flight(Arrays.asList(segment5));

        testFlights.add(flight1);
        testFlights.add(flight2);
        testFlights.add(flight3);
        testFlights.add(flight4);
        String headString = "\n ****** Список без перелётов без пересадок  ********* \n";

        List<Flight> newTestFlights = FlightService.flightsWithoutFlightsTransferWithoutTransfers(testFlights);

        String expectedOutput = System.lineSeparator() + flight4.toString() + System.lineSeparator();

        assertEquals(1, newTestFlights.size());
        assertTrue(newTestFlights.contains(flight4));
        assertEquals(headString + expectedOutput, outputStreamCaptor.toString());
    }
}