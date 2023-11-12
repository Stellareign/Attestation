package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

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

    public static List<Flight> flightsWithoutFlightDepartingInThePast(List<Flight> airTravel) {

        List<Flight> newFlightList = new ArrayList<>();
        System.out.println("\n **************** Список без перелётов с вылетом в прошлом  ******************** \n");
        for (Flight flight : airTravel) {
            if (flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate()
                    .isAfter(LocalDateTime.now()))) {
                newFlightList.add(flight);
            }
        }
        newFlightList.forEach(System.out::println);
        return newFlightList;
    }

    /**
     * Список перелётов без перелёта, в котором дата вылета позже даты прилёта
     *
     * @param airTravel
     */

    public static List<Flight> flightsWithoutFlightWereArrivalBeforeDeparture(List<Flight> airTravel) {
        List<Flight> newFlightList = new ArrayList<>();
        System.out.println("\n **************** Список без перелётов с прилётом раньше вылета  ***************\n");

        for (Flight flight : airTravel) {
            if (flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate()
                    .isBefore(segment.getArrivalDate()))) {
                newFlightList.add(flight);
            }
        }
        newFlightList.forEach(System.out::println);
        return newFlightList;
    }


    /**
     * Список перелётов, в котором общее время пересадок не превышает двух часов
     *
     * @param airTravel
     */

    public static List<Flight> flightsWithoutFlightWereTransferMoreThanTwoHours(List<Flight> airTravel) {
        System.out.println("\n ****** Список без перелётов, в которых общее время пересадок длится более 2-х часов  ********* \n");
        List<Flight> newFlightList = new ArrayList<>();

        for (Flight flight : airTravel) {
            long sum = 0;
            for (int i = 0; i < flight.getSegments().size()-1; i++) {
               long  duration = Duration.between(flight.getSegments().get(i).getArrivalDate(),
                        (flight.getSegments().get(i+1).getDepartureDate())).toHours();
                sum += duration;
                }
            if (sum < 2 && flight.getSegments().size() > 1 ) {
                    newFlightList.add(flight);
            }
        }
        newFlightList.forEach(System.out::println);
        return newFlightList;
    }

    /**
     * Список перелётов без пересадок
     *
     * @param airTravel
     */
    public static List<Flight> flightsWithoutFlightsTransferWithoutTransfers(List<Flight> airTravel) {
        Set<Flight> newFlightList = new HashSet<>();
        System.out.println("\n ****** Список без перелётов без пересадок  ********* \n");
        for (Flight flight : airTravel) {

            if (flight.getSegments().size() == 1) {
                newFlightList.add(flight);
            }
        }
        newFlightList.forEach(System.out::println);
        return newFlightList.stream().toList();
    }
}
