package com.finStream.bankmanagementservice.dto.bank;

import com.finStream.bankmanagementservice.entity.Image;
import com.finStream.bankmanagementservice.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) for User.
 * This class is used to transfer bank data within the application,
 * typically when fetching data from or sending data to the database.
 * It encapsulates bank-related information like id, name, shortName, email, and password.
 *
 * @Data Generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 * @AllArgsConstructor Generates a constructor with arguments for all fields in the class.
 * @NoArgsConstructor Generates a default constructor with no arguments.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private UUID id;
    private String name;
    private String shortName;
    private Image image;
    private boolean verified;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String address;
    private String email;
    private String phoneNumber;
}
