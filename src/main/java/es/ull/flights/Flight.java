/**
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 *  * @author Marcelo Daniel Choque Mamani
 *  * @version 1.0
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @file Flight.java
 * @brief Represents a flight in the flight management system.
 *
 * The Flight class manages flight information, including the flight number,
 * the number of seats available, and the list of passengers associated with the flight.
 * It also handles operations such as adding and removing passengers.
 *
 * @details
 * This class ensures that flight numbers adhere to a specific format and that
 * passengers can only be added if seats are available. It provides functionality
 * to manage the passengers and retrieve details about the flight.
 *
 * @author Marcelo Daniel Choque Mamani
 * @version 1.0
 * @date 2024-12-20
 */

public class Flight {

    private String flightNumber; ///< The flight number.
    private int seats; ///< The total number of seats in the flight.
    private Set<Passenger> passengers = new HashSet<>(); ///< Set of passengers on the flight.

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; ///< Regular expression for validating flight numbers.
    private static Pattern pattern = Pattern.compile(flightNumberRegex); ///< Pattern object for flight number validation.

    /**
     * @brief Constructor for the Flight class.
     *
     * Initializes a new Flight object with the specified flight number and seats.
     *
     * @param flightNumber The flight number to set for the flight.
     * @param seats The total number of seats available in the flight.
     * @throws RuntimeException if the provided flight number is invalid.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Getter for the flight number.
     *
     * @return The flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Getter for the number of passengers on the flight.
     *
     * @return The number of passengers on the flight.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Adds a passenger to the flight.
     *
     * This method adds the specified passenger to the flight if there are
     * available seats. Otherwise, it throws a RuntimeException.
     *
     * @param passenger The passenger to be added to the flight.
     * @return true if the passenger is added successfully, false otherwise.
     * @throws RuntimeException if there are not enough seats for the flight.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Removes a passenger from the flight.
     *
     * This method removes the specified passenger from the flight and updates
     * the passenger's flight association to null.
     *
     * @param passenger The passenger to be removed from the flight.
     * @return true if the passenger is removed successfully, false otherwise.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}