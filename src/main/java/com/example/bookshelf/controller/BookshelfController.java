package com.example.bookshelf.controller;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.BookshelfDTO;
import com.example.bookshelf.service.BookshelfService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BookshelfController {

    private final BookshelfService bookshelfService;

    public BookshelfController(BookshelfService bookshelfService){
        this.bookshelfService = bookshelfService;
    }
    @GetMapping("/bookshelf")
    public BookshelfDTO getBookshelf(){
        return bookshelfService.generateBookshelfDTO(bookshelfService.getAllBooksAsBookDTO());
    }

    @PostMapping("/bookshelf/add")
    public BookshelfDTO addNewBookToBookshelf(@RequestBody BookDTO bookDTO){
        return bookshelfService.addNewBookToBookshelf(bookDTO);
    }

    @PutMapping("/bookshelf/update")
    public BookshelfDTO updateBookInBookshelf(@RequestBody BookDTO bookDTO) throws Exception {
        return bookshelfService.generateBookshelfDTO(bookshelfService.updateBookInBookshelf(bookDTO));
    }

    @DeleteMapping("bookshelf/delete")
    public BookshelfDTO deleteBookFromBookshelf(@RequestBody BookDTO bookDTO) throws Exception {
        return bookshelfService.generateBookshelfDTO(bookshelfService.deleteBookFromBookshelf(bookDTO));
    }
}
