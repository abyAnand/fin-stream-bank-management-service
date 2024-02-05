package com.finStream.bankmanagementservice.entity.loan;

import com.finStream.bankmanagementservice.enums.LoanCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private UUID bankId;

    private String name;

    @Enumerated(EnumType.STRING)
    private LoanCategory loanCategory;

    boolean deleted;
}
