package com.finStream.bankmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents a standardized structure for sending error messages from the application.
 * This class is used to encapsulate details about an error that occurs within the application,
 * providing necessary information like the timestamp of the error, the HTTP status code,
 * a descriptive error message, and the path where the error occurred.
 *
 * @Data Generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 * @AllArgsConstructor Generates a constructor with arguments for all fields in the class.
 * @NoArgsConstructor Generates a default constructor with no arguments.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private LocalDate timeStamp; // The date when the error occurred
    private int status;          // The HTTP status code of the error
    private String error;        // A brief description of the error status
    private String message;      // A detailed error message
    private String path;         // The API path where the error occurred

}

