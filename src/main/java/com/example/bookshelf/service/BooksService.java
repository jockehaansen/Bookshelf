package com.example.bookshelf.service;

import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BooksService {
    void saveBookFromGoogleBooks(SaveBookDTO saveBookDTO);
    Book saveBookDTOToBook(SaveBookDTO saveBookDTO);
}
