package com.libraray.service;

import com.libraray.dto.BookDTO;

import java.util.List;

public interface BookService {

    //addBook
     BookDTO addBook(BookDTO bookDTO);

    //getBookById
    BookDTO  getBookById(Long bookId);

    //updateBook
    BookDTO updateBook(Long bookId, BookDTO bookDTO);

    //Delete
    BookDTO deleteBook(Long bookId);

    List<BookDTO> getAllBooks();

    //search
    List<BookDTO> searchBooks(String title, String author);
}
