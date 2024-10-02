package com.example.bookshelf.service;

import com.example.bookshelf.dto.SaveBookDTO;
import org.springframework.stereotype.Service;

@Service
public interface BooksService {
    void saveBookFromGoogleBooks(SaveBookDTO saveBookDTO);
}
