package com.libraray.service.impl;

import com.libraray.dto.BookDTO;
import com.libraray.entity.BookEntity;
import com.libraray.exception.ResourceNotFoundException;
import com.libraray.repository.BookRepository;
import com.libraray.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return modelMapper.map(savedBook, BookDTO.class);
    }


    @Override
    public BookDTO getBookById(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        // Fetch the existing book from the database
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        // Check and update each field if present in the DTO
        if (bookDTO.getTitle() != null) {
            existingBook.setTitle(bookDTO.getTitle());
        }
        if (bookDTO.getAuthor() != null) {
            existingBook.setAuthor(bookDTO.getAuthor());
        }
        if (bookDTO.getIsbn() != null) {
            existingBook.setIsbn(bookDTO.getIsbn());
        }
        if (bookDTO.getGenre() != null) {
            existingBook.setGenre(bookDTO.getGenre());
        }
        if (bookDTO.getPublisher() != null) {
            existingBook.setPublisher(bookDTO.getPublisher());
        }
        if (bookDTO.getPublicationDate() != null) {
            existingBook.setPublicationDate(bookDTO.getPublicationDate());
        }
        if (bookDTO.getSummary() != null) {
            existingBook.setSummary(bookDTO.getSummary());
        }

        // Save the updated book entity
        BookEntity updatedBook = bookRepository.save(existingBook);

        // Return the updated book as a DTO
        return modelMapper.map(updatedBook, BookDTO.class);
    }


    @Override
    public BookDTO deleteBook(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        bookRepository.delete(bookEntity);
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<BookDTO> searchBooks(String title, String author) {
        List<BookEntity> books = bookRepository.findByTitleAndAuthor(title, author);

        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found matching the search criteria.");
        }

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

}

