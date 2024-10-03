package com.example.bookshelf;

import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.bookshelf.testUtilities.TestDataFactory.createBook;
import static com.example.bookshelf.testUtilities.TestDataFactory.createSaveBookDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BooksServiceTests {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BooksService booksService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void init(){
        assertNotNull(bookRepository);
        assertNotNull(booksService);
    }

    @Test
    void saveBooksFromGoogleBooks_shouldSaveBook(){
        SaveBookDTO saveBookDTO = createSaveBookDTO("Title");
        Book book = createBook(null, "Title", false);
        when(bookRepository.findByVolumeInfo_Title(saveBookDTO.getVolumeInfo().getTitle())).thenReturn(null);
        when(modelMapper.map(saveBookDTO, Book.class)).thenReturn(book);

        booksService.saveBookFromGoogleBooks(saveBookDTO);

        verify(bookRepository, times(1)).findByVolumeInfo_Title(saveBookDTO.getVolumeInfo().getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void saveBooksFromGoogleBooks_shouldNotSaveBook(){
        SaveBookDTO saveBookDTO = createSaveBookDTO("Title");
        Book book = createBook(null, "Title", true);
        when(bookRepository.findByVolumeInfo_Title(saveBookDTO.getVolumeInfo().getTitle())).thenReturn(book);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            booksService.saveBookFromGoogleBooks(saveBookDTO);
        });

        assertEquals("Book already in bookshelf", thrown.getMessage());
        verify(bookRepository, times(1)).findByVolumeInfo_Title(saveBookDTO.getVolumeInfo().getTitle());
        verify(bookRepository, never()).save(any(Book.class));
    }
}
