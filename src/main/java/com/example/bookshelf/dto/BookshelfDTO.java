package com.example.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfDTO {
    private List<BookDTO> books;

    private int totalPagesRead;

    private int totalBooksRead;

    private int totalBooksInBookshelf;
}
