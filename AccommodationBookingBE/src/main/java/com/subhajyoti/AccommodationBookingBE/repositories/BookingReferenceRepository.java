package com.subhajyoti.AccommodationBookingBE.repositories;

import com.subhajyoti.AccommodationBookingBE.entities.BookingReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingReferenceRepository extends JpaRepository<BookingReference, Long> {

    Optional<BookingReference> findByReferenceNo(String referenceNo);

}
