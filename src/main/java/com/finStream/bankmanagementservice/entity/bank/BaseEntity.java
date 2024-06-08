package com.finStream.bankmanagementservice.entity.bank;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The BaseEntity class serves as a base entity for other entities in the application.
 * It is annotated with @MappedSuperclass, indicating that it is not meant to be
 * instantiated on its own but provides common fields and behavior for other entities.
 * It also listens to auditing events using @EntityListeners(AuditingEntityListener.class),
 * enabling automatic population of 'createdDate' and 'updateDate' fields.
 *
 * Fields in this class:
 * - 'id': Represents the primary identifier for entities and is generated using UUID.
 * - 'createdDate': Automatically populated with the creation timestamp.
 * - 'updateDate': Automatically updated with the last modification timestamp.
 * - 'deleted': A boolean flag that can be used to mark an entity as deleted without
 *   physically removing it from the database.
 *
 * Note: This class doesn't have a table associated with it, but its fields and behavior
 * can be inherited by other entity classes in the application.
 */

@Getter
@Setter
@MappedSuperclass
@ToString
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    private boolean deleted;
    private boolean blocked;

}
