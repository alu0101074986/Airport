/*
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
 */

/**
 * @mainpage Airport Project Documentation
 *
 * @section intro_sec Introducción
 * Este proyecto gestiona vuelos y pasajeros usando clases como `Flight` y `Passenger`.
 *
 * @section usage_sec Uso
 * - Crear un objeto `Flight` con un número de vuelo y asientos.
 * - Agregar pasajeros al vuelo usando la clase `Passenger`.
 *
 * @section author_sec Autor
 * Autor: Marcelo Daniel Choque Mamani
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import es.ull.flights.Flight;

/**
 * @file Passenger.java
 * @brief Represents a passenger in the flight system.
 *
 * This class handles passenger attributes and provides functionalities for
 * associating passengers with flights.
 *
 * @author Marcelo Daniel Choque Mamani
 * @version 1.0
 */
public class Passenger {

    private String identifier; ///< Unique identifier for the passenger.
    private String name; ///< Name of the passenger.
    private String countryCode; ///< ISO country code of the passenger's nationality.
    private Flight flight; ///< Flight associated with the passenger.

    /**
     * @brief Constructor for the Passenger class.
     *
     * Initializes a new Passenger object with the provided identifier, name,
     * and country code. Validates the country code against ISO standards.
     *
     * @param identifier Unique identifier for the passenger.
     * @param name Name of the passenger.
     * @param countryCode ISO country code of the passenger's nationality.
     * @throws RuntimeException If the country code is invalid.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Gets the passenger's identifier.
     *
     * @return The unique identifier of the passenger.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Gets the passenger's name.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Gets the passenger's country code.
     *
     * @return The ISO country code of the passenger's nationality.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Gets the flight associated with the passenger.
     *
     * @return The flight object associated with the passenger.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Joins a flight.
     *
     * The passenger leaves their current flight (if any) and joins the specified flight.
     * Handles removal from the previous flight and addition to the new flight.
     *
     * @param flight The flight the passenger wants to join.
     * @throws RuntimeException If the passenger cannot be removed or added to a flight.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets the flight for the passenger.
     *
     * Updates the flight associated with the passenger.
     *
     * @param flight The new flight for the passenger.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Converts the passenger information to a string.
     *
     * @return A string representation of the passenger's details.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
