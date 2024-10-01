package com.example.bookshelf;

import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.BookshelfDTO;
import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.VolumeInfo;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.service.BookshelfService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.example.bookshelf.testUtilities.TestDataFactory.createBook;
import static com.example.bookshelf.testUtilities.TestDataFactory.createBookDTO;
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
        Book book1 = createBook(1L, "TestTitle", true);
        Book book2 = createBook(2L, "TestTitle2", false);
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        assertEquals(bookRepository.findAll().size(), bookshelfService.getAllBooksAsBookDTO().size());
        verify(bookRepository, times(2)).findAll();
    }

    @Test
    void addNewBookToBookshelf_shouldAddNewBookToBookshelf(){
        Book book1 = createBook(1L, "TestTitle", true);
        when(bookRepository.save(book1)).thenReturn(book1);

        BookDTO bookToBeSaved = bookshelfService.bookToBookDTO(book1);
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        BookshelfDTO bookshelfDTO = bookshelfService.addNewBookToBookshelf(bookToBeSaved);

        assertNotNull(bookshelfDTO.getBooks().get(0));
        assertEquals(book1.getId(), bookshelfDTO.getBooks().get(0).getId());
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void updateBookInBookshelf_shouldHaveTheUpdatedValues() throws EntityNotFoundException {
        Book book1 = createBook(1L, "TestTitle", true);

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
    void deleteBookFromBookshelf_crudMethodsShouldBeCalled() throws EntityNotFoundException {
        Book book1 = createBook(1L, "TestTitle", true);

        when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        Book foundBook = bookRepository.findById(book1.getId()).orElse(null);
        assertNotNull(foundBook);

        bookshelfService.deleteBookFromBookshelf(bookshelfService.bookToBookDTO(foundBook));

        verify(bookRepository, times(2)).findById(book1.getId());
        verify(bookRepository, times(1)).delete(foundBook);
    }

    @Test
    void getTotalBooksRead_shouldOnlyReturnBooksMarkedAsReadAsInt(){
        BookDTO book1 = createBookDTO(1L, "TestTitle", true);
        BookDTO book2 = createBookDTO(2L, "TestTitle2", false);
        List<BookDTO> books = List.of(book1, book2);

        int result = bookshelfService.getTotalBooksRead(books);
        assertEquals(1, result);
        assertNotEquals(0, result);
    }

    @Test
    void getTotalPagesRead_shouldReturnTotalPagesReadOfBooksFromListAsInt(){
        BookDTO book1 = createBookDTO(1L, "TestTitle", true);
        BookDTO book2 = createBookDTO(2L, "TestTitle2", false);

        List<BookDTO> books = List.of(book1, book2);
        int result = bookshelfService.getTotalPagesRead(books);

        assertEquals(result, book1.getVolumeInfo().getPageCount());
        assertNotEquals(book1.getVolumeInfo().getPageCount()+book2.getVolumeInfo().getPageCount(), result);
    }

    @Test
    void getTotalBooksInBookshelf_shouldReturnIntOfBooksFromList(){
        BookDTO book1 = new BookDTO();
        BookDTO book2 = new BookDTO();
        List<BookDTO> books = List.of(book1, book2);

        int result = bookshelfService.getTotalBooksInBookshelf(books);

        assertEquals(2, result);
        assertNotEquals(0, result);
    }

    @Test
    void generateBookshelfDTO_shouldContainAllValues(){
        Book book1 = createBook(1L, "TestTitle", true);
        Book book2 = createBook(2L, "TestTitle2", false);

        List<Book> books = List.of(book1, book2);
        List<BookDTO> bookDTOS = List.of(bookshelfService.bookToBookDTO(book1), bookshelfService.bookToBookDTO(book2));
        when(bookRepository.findAll()).thenReturn(books);
        BookshelfDTO resultDTO = bookshelfService.generateBookshelfDTO(bookDTOS);

        assertEquals(bookDTOS, resultDTO.getBooks());
        assertEquals(1, resultDTO.getTotalBooksRead());
        assertEquals(book1.getVolumeInfo().getPageCount(), resultDTO.getTotalPagesRead());
        assertEquals(2, resultDTO.getTotalBooksInBookshelf());
    }
}