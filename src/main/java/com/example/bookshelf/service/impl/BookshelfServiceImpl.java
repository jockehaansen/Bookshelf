package com.example.bookshelf.service.impl;

import com.example.bookshelf.configs.MapperConfig;
import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BookshelfService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@Service
public class BookshelfServiceImpl implements BookshelfService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = MapperConfig.modelMapper();
    Logger logger = Logger.getLogger(BookshelfServiceImpl.class.getName());

    public BookshelfServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooksAsBookDTO() {
        return bookRepository.findAll().stream().map(this::bookToBookDTO).toList();
    }

    @Override
    public List<BookDTO> addNewBookToBookshelf(BookDTO bookDTO) {
        Book book = new Book();
        modelMapper.map(bookDTO, book);
        bookRepository.save(book);
        logger.log(Level.INFO, "Book added to the bookshelf {0}", book);
        return getAllBooksAsBookDTO();
    }

    @Override
    public List<BookDTO> updateBookInBookshelf(BookDTO bookDTO) throws Exception {
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new Exception("Book not found"));
        book.setVolumeInfo(bookDTO.getVolumeInfo());
        book.setMarkedAsRead(bookDTO.isMarkedAsRead());
        bookRepository.save(book);
        return getAllBooksAsBookDTO();
    }

    @Override
    public List<BookDTO> deleteBookFromBookshelf(BookDTO bookDTO) throws Exception {
        logger.log(Level.INFO, "Inside deleteBookFromBookshelf with {0}", bookDTO);
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new Exception("Book not found"));
        logger.log(Level.INFO, "Book found in the repository {0}", book);
        bookRepository.delete(book);
        logger.log(Level.INFO, "Book deleted from the bookshelf {0}", book);
        return getAllBooksAsBookDTO();
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

}
