package com.example.bookshelf.testUtilities;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.VolumeInfo;

import java.util.List;

public class TestDataFactory {
    public static Book createBook(Long id, String title, boolean isMarkedAsRead){
        Book book = new Book();
        book.setId(id);
        book.setMarkedAsRead(isMarkedAsRead);
        book.setVolumeInfo(new VolumeInfo(
                title, "TestSubtitle", List.of("TestAuthors"),
                "2022-02-02", "TestDescription", 222));
        return book;
    }

    public static BookDTO createBookDTO(Long id, String title, boolean isMarkedAsRead){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(id);
        bookDTO.setMarkedAsRead(isMarkedAsRead);
        bookDTO.setVolumeInfo(new VolumeInfo(
                title, "TestSubtitle", List.of("TestAuthors"),
                "2022-02-02", "TestDescription", 222));
        return bookDTO;
    }
}
