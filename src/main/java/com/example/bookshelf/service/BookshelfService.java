package com.example.bookshelf.service;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.BookshelfDTO;
import com.example.bookshelf.model.Book;

import java.util.List;

public interface BookshelfService {
    List<BookDTO> getAllBooksAsBookDTO();

    BookshelfDTO addNewBookToBookshelf(BookDTO bookDTO);

    List<BookDTO> updateBookInBookshelf(BookDTO bookDTO) throws Exception;

    List<BookDTO> deleteBookFromBookshelf(BookDTO bookDTO) throws Exception;

    BookDTO bookToBookDTO(Book book);

    int getTotalPagesRead(List<BookDTO> books);
    int getTotalBooksRead(List<BookDTO> books);
    int getTotalBooksInBookshelf(List<BookDTO> books);

    BookshelfDTO generateBookshelfDTO(List<BookDTO> bookDTOS);

    List<BookDTO> sortBookDTOsByTitle(List<BookDTO> bookDTOS);

}
