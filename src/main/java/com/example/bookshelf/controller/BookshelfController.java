package com.example.bookshelf.controller;

import com.example.bookshelf.dto.BookDTO;
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
    public List<BookDTO> getBooksInBookshelf(){
        return bookshelfService.getAllBooksAsBookDTO();
    }

    @PostMapping("/bookshelf/add")
    public List<BookDTO> addNewBookToBookshelf(@RequestBody BookDTO bookDTO){
        return bookshelfService.addNewBookToBookshelf(bookDTO);
    }

    @PutMapping("/bookshelf/update")
    public List<BookDTO> updateBookInBookshelf(@RequestBody BookDTO bookDTO) throws Exception {
        return bookshelfService.updateBookInBookshelf(bookDTO);
    }

    @DeleteMapping("bookshelf/delete")
    public List<BookDTO> deleteBookFromBookshelf(@RequestBody BookDTO bookDTO) throws Exception {
        return bookshelfService.deleteBookFromBookshelf(bookDTO);
    }
}
