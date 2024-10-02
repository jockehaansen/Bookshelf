package com.example.bookshelf.controller;

import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.service.BooksService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveBookFromGoogleBooks(@RequestBody SaveBookDTO saveBookDTO) {
        booksService.saveBookFromGoogleBooks(saveBookDTO);
        return ResponseEntity.ok("Book saved successfully in Bookshelf");
    }
}
