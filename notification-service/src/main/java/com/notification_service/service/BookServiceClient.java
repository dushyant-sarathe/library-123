//package com.notification_service.service;
//
//
//import com.libraray.dto.BookDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "book-service", url = "http://localhost:8080")
//public interface BookServiceClient {
//
//    @GetMapping("/api/books/{id}")
//    BookDTO getBookById(@PathVariable("id") Long id);
//}
//
