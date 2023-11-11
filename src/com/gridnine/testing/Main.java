package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {

        var airTravel = FlightBuilder.createFlights();

        FlightService.allFlightsList(airTravel);
        FlightService.flightsWithoutFlightDepartingInThePast(airTravel);
        FlightService.flightsWithoutFlightWereArrivalBeforeDeparture(airTravel);
        FlightService.flightsWithoutFlightWereTransferMoreThanTwoHours(airTravel);
    }
}