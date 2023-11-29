package com.finStream.bankmanagementservice.entity;


import com.finStream.bankmanagementservice.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

/**
 * The Bank class represents a financial institution within the application.
 * It extends the BaseEntity class, inheriting common entity fields and auditing capabilities.
 *
 * Fields in this class:
 * - 'name': Represents the full name of the bank.
 * - 'shortName': Represents a short or abbreviated name for the bank.
 * - 'verified': Indicates whether the bank is verified or not (e.g., for authenticity).
 * - 'status': Represents the status of the bank, which could be an enum or custom type.
 * - 'address': Represents the address of the bank (Note: Currently commented out).
 * - 'email': Represents the email address associated with the bank.
 * - 'phoneNumber': Represents the contact phone number for the bank.
 *
 * This class uses Lombok annotations for generating getters, setters, constructors,
 * and a toString method, reducing boilerplate code.
 * The 'address' field
 *  OneToOne relationship to an Address entity.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Bank extends BaseEntity {

    private String name;
    private String shortName;
    private boolean verified;
    private Status status;

    //TODO: add bank address. also uncomment in dto.

//    @OneToOne
//    @JoinColumn(name = "address_id")
    private String address;
    private String email;
    private String password;
    private String phoneNumber;
}
