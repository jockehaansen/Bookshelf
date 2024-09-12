package com.example.bookshelf.controller;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookshelfController {

    @Autowired
    private BookshelfService bookshelfService;
    @GetMapping("/bookshelf")
    public List<BookDTO> getBooksInBookshelf(){
        return bookshelfService.getAllBooksAsBookDTO();
    }

    @PostMapping("/bookshelf/add")
    public List<BookDTO> addNewBookToBookshelf(@RequestBody BookDTO bookDTO){
        return bookshelfService.addNewBookToBookshelf(bookDTO);
    }
}
