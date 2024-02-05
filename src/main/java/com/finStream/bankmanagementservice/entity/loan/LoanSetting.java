package com.finStream.bankmanagementservice.entity.loan;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanSetting {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    UUID loanTypeId;

    private String name;

    private BigDecimal interestRate;

    private double loanAmount;

    private int loanTermMonths;

    private boolean deleted;

    @CreatedDate
    private Date createdAt;

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
//                ", bank=" + bankId + '\'' +
//                ", name=" + name + '\'' +
                ", loanTypeId='" + loanTypeId + '\'' +
                ", interestRate=" + interestRate + '\'' +
                ", loanAmount=" + loanAmount + '\'' +
                ", loanTermMonths=" + loanTermMonths + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
