package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        var airTravel = FlightBuilder.createFlights();


        // все перелёты:
        FlightService.allFlightsList(airTravel);

        // без перелёта с просроченным вылетом:
        FlightService.flightsWithoutFlightDepartingInThePast(airTravel);

        // без перелёта, где вылет позже приземления
        FlightService.flightsWithoutFlightWereArrivalBeforeDeparture(airTravel);


        // Без перелётов, где общее время пересадок больше 2-х часов
        FlightService.flightsWithoutFlightWereTransferMoreThanTwoHours(airTravel);

        // Список перелётов без пересадок
        FlightService.flightsWithoutFlightsTransferWithoutTransfers(airTravel);
    }
}