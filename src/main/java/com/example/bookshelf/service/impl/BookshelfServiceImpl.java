package com.example.bookshelf.service.impl;

import com.example.bookshelf.configs.MapperConfig;
import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BookshelfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Autowired
    private BookRepository bookRepository;
    private final ModelMapper modelMapper = MapperConfig.modelMapper();

    @Override
    public List<BookDTO> getAllBooksAsBookDTO() {
        return bookRepository.findAll().stream().map(this::bookToBookDTO).toList();
    }

    @Override
    public List<BookDTO> addNewBookToBookshelf(BookDTO bookDTO) {
        Book book = bookDTOToBook(bookDTO);
        bookRepository.save(book);
        return getAllBooksAsBookDTO();
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book bookDTOToBook(BookDTO bookDTO){
        return modelMapper.map(bookDTO, Book.class);
    }


}
