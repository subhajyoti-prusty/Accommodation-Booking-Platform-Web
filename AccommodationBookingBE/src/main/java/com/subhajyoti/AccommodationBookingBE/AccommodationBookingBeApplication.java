package com.subhajyoti.AccommodationBookingBE;

import com.subhajyoti.AccommodationBookingBE.dtos.NotificationDTO;
import com.subhajyoti.AccommodationBookingBE.entities.Notification;
import com.subhajyoti.AccommodationBookingBE.enums.NotificationType;
import com.subhajyoti.AccommodationBookingBE.notification.NotificationServices;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@RequiredArgsConstructor
public class AccommodationBookingBeApplication {

//	private	final NotificationServices notificationServices ;
//
//    public AccommodationBookingBeApplication(NotificationServices notificationServices) {
//        this.notificationServices = notificationServices;
//    }

    public static void main(String[] args) {
		SpringApplication.run(AccommodationBookingBeApplication.class, args);
	}

//	@PostConstruct
//	public void DummyEmail(){
//		NotificationDTO notificationDTO = NotificationDTO.builder()
//				.recipient("subhajyoti.2004@gmail.com")
//				.subject("Testing Mail")
//				.body("Its a testing mail for mail notification")
//				.build();
//
//		notificationServices.sendEmail(notificationDTO);
//	}

}
