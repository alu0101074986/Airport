package es.ull.passengers;

import es.ull.flights.Flight;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @class PassengerTest
 * @brief Contains JUnit tests for the Passenger class.
 *
 * The PassengerTest class includes various test cases to verify the correct
 * behavior of the Passenger class methods, such as getters, handling invalid
 * country codes, joining and leaving flights, failure to join a flight, and
 * the toString method.
 */
public class PassengerTest {

    /**
     * @brief Tests getters for the Passenger class.
     */
    @Test
    public void testGetters() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertEquals("ID123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
    }

    /**
     * @brief Tests creating a Passenger object with an invalid country code.
     */
    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID123", "John Doe", "Invalid"));
    }

    /**
     * @brief Tests joining a flight for a Passenger.
     */
    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Tests leaving the previous flight and joining a new flight for a Passenger.
     */
    @Test
    public void testLeavePreviousFlight() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("CD456", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);
        assertEquals(flight2, passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
        assertEquals(1, flight2.getNumberOfPassengers());
    }

    /**
     * @brief Tests joining a flight that exceeds its available seats.
     */
    @Test
    public void testJoinFlightFails() {
        Flight flight1 = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("ID123", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID456", "Jane Doe", "CA");
        passenger1.joinFlight(flight1);
        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight1));
    }

    /**
     * @brief Tests the toString method for the Passenger class.
     */
    @Test
    public void testToString() {
        Passenger passenger1 = new Passenger("ID123", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID456", "Jane Doe", "CA");
        assertEquals("Passenger John Doe with identifier: ID123 from US", passenger1.toString());
        assertEquals("Passenger Jane Doe with identifier: ID456 from CA", passenger2.toString());
    }
}