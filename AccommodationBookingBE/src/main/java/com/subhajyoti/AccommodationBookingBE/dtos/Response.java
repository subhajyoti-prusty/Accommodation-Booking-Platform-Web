package com.subhajyoti.AccommodationBookingBE.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.subhajyoti.AccommodationBookingBE.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    //generic
    private int status;
    private String message;

    //for login
    private String token;
    private UserRole role;
    private Boolean active;
    private String expirationTime;

    //user data output
    private UserDTO user;
    private List<UserDTO> users;

    //Booking data output
    private BookingDTO booking;
    private List<BookingDTO> bookings;

    //Room data output
    private RoomDTO room;
    private List<RoomDTO> rooms;

    //Payment data output
    private String transactionId;
    private PaymentDTO payment;
    private List<PaymentDTO> payments;

    //Payment data output
    private NotificationDTO notification;
    private List<NotificationDTO> notifications;

    private final LocalDateTime timestamp = LocalDateTime.now();

}
