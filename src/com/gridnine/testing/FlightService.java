package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlightService {
    /**
     * Список всех перелётов
     *
     * @param allAirTravel
     */

    public static void allFlightsList(List<Flight> allAirTravel) {
        System.out.println("\n *********************** Список всех перелётов  ****************************** \n");
        allAirTravel.forEach(System.out::println);
    }

    /**
     * Список перелётов без перелётов с просроченной датой вылета
     *
     * @param airTravel
     */

    public static void flightsWithoutFlightDepartingInThePast(List<Flight> airTravel) {

        List<Flight> newFlightList = new ArrayList<>();
        System.out.println("\n **************** Список без перелёта с вылетом в прошлом  ******************** \n");
        for (Flight flight : airTravel) {
            if (flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now())) {
                newFlightList.add(flight);
            }
        }
        newFlightList.forEach(System.out::println);
    }

    /**
     * Список перелётов без перелёта, в котором дата вылета позже даты прилёта
     *
     * @param airTravel
     */

    public static void flightsWithoutFlightWereArrivalBeforeDeparture(List<Flight> airTravel) {
        Set<Flight> newFlightList = new HashSet<>();
        System.out.println("\n **************** Список без перелёта с прилётом раньше вылета  ***************\n");

        for (Flight flight : airTravel) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(segment.getArrivalDate())) {
                    newFlightList.add(flight);
                }
            }
        }
        newFlightList.forEach(System.out::println);
    }

    /**
     * Список перелётов, в котором общее время пересадок не превышает двух часов
     *
     * @param airTravel
     */

    public static void flightsWithoutFlightWereTransferMoreThanTwoHours(List<Flight> airTravel) {
        Set<Flight> newFlightList = new HashSet<>();
        long sum = 0;
        System.out.println("\n ****** Список без перелёта, в котором пересадка длится менее 2-х часов  ********* \n");
        for (Flight flight : airTravel) {
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                var duration = Duration.between(flight.getSegments().get(i).getArrivalDate(), (flight.getSegments().get(i + 1).getDepartureDate())).toHours();
                sum = sum + duration;
                if (sum < 2) {
                    newFlightList.add(flight);
                }
            }
        }
        newFlightList.forEach(System.out::println);
        flightsWithoutFlightWereTransferMoreThanTwoHoursAndWithoutTransfers(airTravel);
    }

    private static void flightsWithoutFlightWereTransferMoreThanTwoHoursAndWithoutTransfers(List<Flight> airTravel) {
        Set<Flight> newFlightList = new HashSet<>();
        for (Flight flight : airTravel) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                if (flight.getSegments().size() == 1) {
                    newFlightList.add(flight);
                }
            }
        } newFlightList.forEach(System.out::println);
    }
}
