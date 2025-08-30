package com.subhajyoti.AccommodationBookingBE.exceptions;

public class InvalidBookingStateAndDateException extends RuntimeException {

    public InvalidBookingStateAndDateException(String message) {
        super(message);
    }

}
