package com.notification_service.controller;

import com.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmailService emailService;

    @PostMapping("/issue")
    public ResponseEntity<String> sendBookIssuedNotification(@RequestBody String email) {
        kafkaTemplate.send("book_issued", email);
        emailService.sendEmail(email, "Book Issued", "A book has been issued to you.");
        return new ResponseEntity<>("Notification sent", HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<String> sendBookReturnNotification(@RequestBody String email) {
        kafkaTemplate.send("book_return", email);
        emailService.sendEmail(email, "Book Return Due", "Your borrowed book is due for return.");
        return new ResponseEntity<>("Notification sent", HttpStatus.OK);
    }
}








//
//    private final NotificationService notificationService;
//
//    @Autowired
//    public NotificationController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    @PostMapping("/book/{bookId}")
//    public ResponseEntity<String> notifyBook(@PathVariable("bookId") Long bookId) {
//        try {
//            notificationService.notifyBookStatus(bookId);
//            return new ResponseEntity<>("Notification sent for book ID: " + bookId, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to send notification: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
