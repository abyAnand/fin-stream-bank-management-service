package com.finStream.bankmanagementservice.entity;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * The Address class represents a physical address with details such as street, city, zip code,
 * country, and contact information. It extends the BaseEntity class, inheriting common fields
 * for entities and their auditing capabilities.
 * /
 * Fields in this class:
 * - 'street': Represents the street address.
 * - 'city': Represents the city or locality.
 * - 'zip': Represents the postal or zip code.
 * - 'country': Represents the country or region.
 * - 'contact': Represents contact information related to the address.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address extends BaseEntity{

    private String street;
    private String city;
    private String zip;
    private String country;
    private String contact;

}
