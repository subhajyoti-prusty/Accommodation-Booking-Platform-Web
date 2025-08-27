package com.subhajyoti.AccommodationBookingBE.entities;

import com.subhajyoti.AccommodationBookingBE.enums.PaymentGateway;
import com.subhajyoti.AccommodationBookingBE.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String transactionId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String bookingReference;

    private String failureReason;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

}
