package com.subhajyoti.AccommodationBookingBE.notification;

import com.subhajyoti.AccommodationBookingBE.dtos.NotificationDTO;

public interface NotificationServices {

    void sendEmail(NotificationDTO notificationDTO);

    void sendSms();

    void sendWhatsapp();
}
