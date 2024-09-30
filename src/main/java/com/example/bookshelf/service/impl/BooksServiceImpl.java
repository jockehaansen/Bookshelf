package com.example.bookshelf.service.impl;

import com.example.bookshelf.configs.MapperConfig;
import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper = MapperConfig.modelMapper();
    Logger logger = Logger.getLogger(BooksServiceImpl.class.getName());


    public BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @Override
    public void saveBookFromGoogleBooks(SaveBookDTO saveBookDTO) {
        logger.log(Level.INFO,"Checking book {0}", saveBookDTO);
        if (bookRepository.findByVolumeInfo_Title(saveBookDTO.getVolumeInfo().getTitle()) != null){
            throw new RuntimeException("Book already in bookshelf");
        }
        logger.log(Level.INFO,"Saving book {0}", saveBookDTO);
        bookRepository.save(saveBookDTOToBook(saveBookDTO));
        logger.log(Level.INFO,"Book saved");
    }

    private Book saveBookDTOToBook(SaveBookDTO saveBookDTO) {
        return modelMapper.map(saveBookDTO, Book.class);
    }
}
