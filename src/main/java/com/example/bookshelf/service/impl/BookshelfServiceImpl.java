package com.example.bookshelf.service.impl;

import com.example.bookshelf.configs.MapperConfig;
import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.BookshelfDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BookshelfService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        return sortBookDTOsByTitle(bookRepository.findAll().stream().map(this::bookToBookDTO).toList());
    }

    @Override
    public BookshelfDTO addNewBookToBookshelf(BookDTO bookDTO) {
        Book book = new Book();
        modelMapper.map(bookDTO, book);
        bookRepository.save(book);
        logger.log(Level.INFO, "Book saved successfully: {0}", book.getVolumeInfo().getTitle());
        return generateBookshelfDTO(getAllBooksAsBookDTO());
    }

    @Override
    public List<BookDTO> updateBookInBookshelf(BookDTO bookDTO) throws EntityNotFoundException {
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException("Book with title: " + bookDTO.getVolumeInfo().getTitle() + " not found"));
        book.setVolumeInfo(bookDTO.getVolumeInfo());
        book.setMarkedAsRead(bookDTO.isMarkedAsRead());
        bookRepository.save(book);
        logger.log(Level.INFO, "Book updated successfully: {0}", book.getVolumeInfo().getTitle());
        return getAllBooksAsBookDTO();
    }

    @Override
    public List<BookDTO> deleteBookFromBookshelf(BookDTO bookDTO) throws EntityNotFoundException {
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(
                () -> new EntityNotFoundException("Book with title: " + bookDTO.getVolumeInfo().getTitle() + " not found"));
        logger.log(Level.INFO, "Book found in the repository, deleting: {0}", book.getVolumeInfo().getTitle());
        bookRepository.delete(book);
        logger.log(Level.INFO, "Book successfully deleted: {0}", book.getVolumeInfo().getTitle());
        return getAllBooksAsBookDTO();
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public int getTotalPagesRead(List<BookDTO> books) {
        return books.stream()
                .filter(BookDTO::isMarkedAsRead)
                .map(bookDTO -> bookDTO.getVolumeInfo().getPageCount())
                .reduce(0, Integer::sum);
    }

    @Override
    public int getTotalBooksRead(List<BookDTO> books) {
        return books.stream().filter(BookDTO::isMarkedAsRead).toList().size();
    }

    @Override
    public int getTotalBooksInBookshelf(List<BookDTO> books) {
        return books.size();
    }

    @Override
    public BookshelfDTO generateBookshelfDTO(List<BookDTO> bookDTOS) {
        return new BookshelfDTO(bookDTOS, getTotalPagesRead(bookDTOS),
                getTotalBooksRead(bookDTOS), getTotalBooksInBookshelf(bookDTOS));
    }

    @Override
    public List<BookDTO> sortBookDTOsByTitle(List<BookDTO> bookDTOS) {
        List<BookDTO> sortingList = new ArrayList<>(bookDTOS);
        sortingList.sort(Comparator.comparing(bookDTO -> bookDTO.getVolumeInfo().getTitle()));
        return sortingList;
    }

}
