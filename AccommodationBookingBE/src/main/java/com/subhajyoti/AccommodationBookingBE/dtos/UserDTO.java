package com.subhajyoti.AccommodationBookingBE.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.subhajyoti.AccommodationBookingBE.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private long id;

    private String email;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private String phoneNumber;

    private UserRole role;

    private boolean active;

    private LocalDate createdAt;
}
