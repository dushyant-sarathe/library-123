//package com.libraray.service;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationConsumer {
//
//    @KafkaListener(topics = "notifications", groupId = "notification-group")
//    public void listen(String message) {
//        // Process the received message
//        System.out.println("Received Notification: " + message);
//        // Here, you can implement additional logic (e.g., sending emails, SMS, etc.)
//    }
//}
