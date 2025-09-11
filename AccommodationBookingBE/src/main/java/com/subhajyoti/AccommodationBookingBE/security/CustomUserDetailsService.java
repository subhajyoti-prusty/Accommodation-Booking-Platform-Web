package com.subhajyoti.AccommodationBookingBE.security;

import com.subhajyoti.AccommodationBookingBE.entities.User;
import com.subhajyoti.AccommodationBookingBE.exceptions.NotFoundException;
import com.subhajyoti.AccommodationBookingBE.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
