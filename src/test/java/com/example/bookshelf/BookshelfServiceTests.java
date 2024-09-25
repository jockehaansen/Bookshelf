package com.example.bookshelf;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.VolumeInfo;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BookshelfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookshelfServiceTests {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookshelfService bookshelfService;

    @BeforeEach
    void init(){
        assertNotNull(bookRepository);
        assertNotNull(bookshelfService);
    }

    @Test
    void getAllBooksAsBookDTO_shouldReturnSameLengthList(){
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        assertEquals(bookRepository.findAll().size(), bookshelfService.getAllBooksAsBookDTO().size());
        verify(bookRepository, times(2)).findAll();
    }

    @Test
    void addNewBookToBookshelf_shouldAddNewBookToBookshelf(){
        Book book1 = new Book();
        book1.setId(1L);
        when(bookRepository.save(book1)).thenReturn(book1);

        Book savedBook = bookRepository.save(book1);

        assertNotNull(savedBook);
        assertEquals(book1.getId(), savedBook.getId());
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void updateBookInBookshelf_shouldHaveTheUpdatedValues() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setVolumeInfo(new VolumeInfo(
                "TestTitle", "TestSubtitle", List.of("TestAuthors"),
                "2022-02-02", "TestDescription", 222));

        BookDTO incomingDTO = new BookDTO();
        incomingDTO.setId(1L);
        incomingDTO.setVolumeInfo(new VolumeInfo("ChangedTitle", "ChangedSubtitle",
                List.of("ChangedAuthor"), "2023-03-03", "ChangedDescription", 111));

        when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        List<BookDTO> updatedBooks = bookshelfService.updateBookInBookshelf(incomingDTO);

        assertNotNull(updatedBooks);
        assertEquals("ChangedTitle", book1.getVolumeInfo().getTitle());
        assertEquals("ChangedSubtitle", book1.getVolumeInfo().getSubtitle());
        assertEquals(List.of("ChangedAuthor"), book1.getVolumeInfo().getAuthors());
        assertEquals("ChangedDescription", book1.getVolumeInfo().getDescription());
        assertEquals(111, book1.getVolumeInfo().getPageCount());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void deleteBookFromBookshelf_crudMethodsShouldBeCalled() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);

        when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        Book foundBook = bookRepository.findById(book1.getId()).orElse(null);
        assertNotNull(foundBook);

        bookshelfService.deleteBookFromBookshelf(bookshelfService.bookToBookDTO(foundBook));

        verify(bookRepository, times(2)).findById(book1.getId());
        verify(bookRepository, times(1)).delete(foundBook);
    }
}