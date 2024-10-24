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
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        modelMapper.map(bookDTO, existingBook); // This automatically updates the fields in existingBook

        BookEntity updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    @Override
    public BookDTO deleteBook(Long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        bookRepository.delete(bookEntity);
        return modelMapper.map(bookEntity, BookDTO.class);
    }

//    @Override
//    public List<BookDTO> searchBooks(String title, String author) {
//        List<BookEntity> books = bookRepository.findByTitle(title, author);
//
//        if (books.isEmpty()) {
//            throw new ResourceNotFoundException("No books found matching the search criteria.");
//        }
//
//        return books.stream()
//                .map(book -> modelMapper.map(book, BookDTO.class))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("No books found"));
//    }
}

