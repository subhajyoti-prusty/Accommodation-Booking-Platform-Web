package com.subhajyoti.AccommodationBookingBE.entities;
import com.subhajyoti.AccommodationBookingBE.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "Room number is not valid")
    @Column(unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @DecimalMin(value = "0.1", message = "Price per night is required")
    private BigDecimal pricePerNight;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    private String description;

    private String imageUrl;
}
