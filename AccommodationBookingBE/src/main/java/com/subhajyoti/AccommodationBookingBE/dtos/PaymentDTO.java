package com.subhajyoti.AccommodationBookingBE.dtos;

import com.subhajyoti.AccommodationBookingBE.enums.PaymentGateway;
import com.subhajyoti.AccommodationBookingBE.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private Long id;

    private BookingDTO booking;

    private String transactionId;

    private BigDecimal amount;

    private PaymentGateway paymentMethod; //e,g Paypal. Stripe, Paystack, Razorpay

    private LocalDateTime paymentDate;

    private PaymentStatus status;

    private String bookingReference;

    private String failureReason;

    private String approvalLink; //paypal payment approval UEL

}
