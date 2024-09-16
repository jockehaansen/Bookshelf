package com.example.bookshelf.service;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.VolumeInfo;

import java.util.List;

public interface BookshelfService {
    List<BookDTO> getAllBooksAsBookDTO();

    List<BookDTO> addNewBookToBookshelf(VolumeInfo volumeInfo);

    BookDTO bookToBookDTO(Book book);
}
