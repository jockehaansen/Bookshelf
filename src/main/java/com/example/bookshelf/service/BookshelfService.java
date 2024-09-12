package com.example.bookshelf.service;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;

import java.util.List;

public interface BookshelfService {
    public List<BookDTO> getAllBooksAsBookDTO();

    public List<BookDTO> addNewBookToBookshelf(BookDTO bookDTO);

    public BookDTO bookToBookDTO(Book book);
}
