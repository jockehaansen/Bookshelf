package com.example.bookshelf.service;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;

import java.util.List;

public interface BookshelfService {
    List<BookDTO> getAllBooksAsBookDTO();

    List<BookDTO> addNewBookToBookshelf(BookDTO bookDTO);

    List<BookDTO> updateBookInBookshelf(BookDTO bookDTO) throws Exception;

    List<BookDTO> deleteBookFromBookshelf(BookDTO bookDTO) throws Exception;

    BookDTO bookToBookDTO(Book book);
}
