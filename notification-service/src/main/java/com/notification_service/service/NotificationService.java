//package com.notification_service.service;
//
//import com.libraray.dto.BookDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationService {
//
//
//    private final BookServiceClient bookServiceClient;
//
//    @Autowired
//    public NotificationService(BookServiceClient bookServiceClient) {
//        this.bookServiceClient = bookServiceClient;
//    }
//
//    public void notifyBookStatus(Long bookId) {
//        BookDTO book = bookServiceClient.getBookById(bookId);
//        // Logic to send notifications based on the book details
//        System.out.println("Notification sent for book: " + book.getTitle());
//    }
//}
//
////        private final KafkaTemplate<String, String> kafkaTemplate;
////
////        @Autowired
////        public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
////            this.kafkaTemplate = kafkaTemplate;
////        }
////
////        public void sendNotification(String message) {
////            kafkaTemplate.send("notifications", message);
////        }
////    }
//
//
//
