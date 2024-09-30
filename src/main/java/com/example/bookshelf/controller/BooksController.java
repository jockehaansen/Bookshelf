package com.example.bookshelf.controller;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.service.BooksService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping("/books/save")
    public void saveBookFromGoogleBooks(@RequestBody SaveBookDTO saveBookDTO) {
        booksService.saveBookFromGoogleBooks(saveBookDTO);
    }
}
