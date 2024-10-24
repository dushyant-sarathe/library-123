package com.libraray.controller;


import com.libraray.dto.BookDTO;
import com.libraray.dto.Response;
import com.libraray.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint to add a new book
    @PostMapping
    public ResponseEntity<Response> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.addBook(bookDTO);

        // Create Response object
        Response response = new Response();
        response.setBookId(savedBook.getBookId());  // Assuming bookId is available after saving
        response.setMessage("Book added successfully!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint to get a book by its ID
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        BookDTO bookDTO = bookService.getBookById(bookId);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    // Endpoint to update an existing book
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    // Endpoint to delete a book by its ID
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

//    // Endpoint to search for books by title and author
//    @GetMapping("/search")
//    public ResponseEntity<List<BookDTO>> searchBooks(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String author) {
//        List<BookDTO> books = bookService.searchBooks(title, author);
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }
}

