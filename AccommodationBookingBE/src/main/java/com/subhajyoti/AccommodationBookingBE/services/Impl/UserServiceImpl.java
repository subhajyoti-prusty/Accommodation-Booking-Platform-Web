package com.subhajyoti.AccommodationBookingBE.services.Impl;

import com.subhajyoti.AccommodationBookingBE.dtos.*;
import com.subhajyoti.AccommodationBookingBE.entities.Booking;
import com.subhajyoti.AccommodationBookingBE.entities.User;
import com.subhajyoti.AccommodationBookingBE.enums.UserRole;
import com.subhajyoti.AccommodationBookingBE.exceptions.InvalidCredentialException;
import com.subhajyoti.AccommodationBookingBE.exceptions.NotFoundException;
import com.subhajyoti.AccommodationBookingBE.repositories.BookingRepository;
import com.subhajyoti.AccommodationBookingBE.repositories.UserRepository;
import com.subhajyoti.AccommodationBookingBE.security.JwtUtils;
import com.subhajyoti.AccommodationBookingBE.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final ModelMapper modelMapper;

    private final BookingRepository bookingRepository;

    @Override
    public Response registerUser(RegistrationRequest registrationRequest) {

        log.info("Inside registerUser");

        UserRole role = UserRole.CUSTOMER;

        if (registrationRequest.getRole() != null) {
            role = registrationRequest.getRole();
        }

        User userToSave = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .active(true)
                .build();

        userRepository.save(userToSave);

        return Response.builder()
                .status(200)
                .message("User Registered Successfully")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {

        log.info("Inside loginUser");

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password does not match");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return Response.builder()
                .status(200)
                .message("Login Successful")
                .role(user.getRole())
                .token(token)
                .active(user.isActive())
                .expirationTime("6 Month")
                .build();
    }

    @Override
    public Response getAllUser() {

        log.info("Inside getAllUser");

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<UserDTO> userDTOList = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Success")
                .users(userDTOList)
                .build();
    }

    @Override
    public Response getOwnAccountDetails() {

        log.info("Inside getOwnAccountDetails");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return Response.builder()
                .status(200)
                .message("Success")
                .user(userDTO)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {

        log.info("Inside getCurrentLoggedInUser");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    @Override
    public Response updateOwnAccount(UserDTO userDTO) {
        log.info("Inside updateOwnAccount");

        User existingUser = getCurrentLoggedInUser();

        if ( userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if ( userDTO.getFirstName() != null) {
            existingUser.setFirstName(userDTO.getFirstName());
        }
        if ( userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        }
        if ( userDTO.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(existingUser);

        return Response.builder()
                .status(200)
                .message("User Updated Successfully")
                .build();
    }

    @Override
    public Response deleteOwnAccount() {
        log.info("Inside deleteOwnAccount");

        User currentUser = getCurrentLoggedInUser();

        userRepository.delete(currentUser);

        return Response.builder()
                .status(200)
                .message("User Deleted Successfully")
                .build();
    }

    @Override
    public Response getMyBookingHistory() {
        log.info("Inside getMyBookingHistory");

        User currentUser = getCurrentLoggedInUser();

        List<Booking> bookingList = bookingRepository.findByUserId(currentUser.getId());

        List<BookingDTO> bookingDTOList = modelMapper.map(bookingList,new TypeToken<List<BookingDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Success")
                .bookings(bookingDTOList)
                .build();
    }
}
