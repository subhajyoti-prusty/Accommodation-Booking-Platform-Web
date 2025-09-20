package com.subhajyoti.AccommodationBookingBE.services;

import com.subhajyoti.AccommodationBookingBE.dtos.LoginRequest;
import com.subhajyoti.AccommodationBookingBE.dtos.RegistrationRequest;
import com.subhajyoti.AccommodationBookingBE.dtos.Response;
import com.subhajyoti.AccommodationBookingBE.dtos.UserDTO;
import com.subhajyoti.AccommodationBookingBE.entities.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUser();

    Response getOwnAccountDetails();

    User getCurrentLoggedInUser();

    Response updateOwnAccount(UserDTO userDTO);

    Response deleteOwnAccount();

    Response getMyBookingHistory();

}
