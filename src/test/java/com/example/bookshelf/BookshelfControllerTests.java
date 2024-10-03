package com.example.bookshelf;

import com.example.bookshelf.controller.BookshelfController;
import com.example.bookshelf.dto.BookDTO;
import com.example.bookshelf.dto.BookshelfDTO;
import com.example.bookshelf.service.BookshelfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.bookshelf.testUtilities.TestDataFactory.createBookDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookshelfController.class)
class BookshelfControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookshelfService bookshelfService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void init(){
        assertNotNull(bookshelfService);

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BookshelfController(bookshelfService)).build();
    }

    @Test
    void getBookshelf_shouldReturnBookshelfDTO() throws Exception{
        mockMvc.perform(get("/bookshelf")).andExpect(status().isOk());
    }

    @Test
    void addNewBookToBookshelf_shouldReturnBookshelfDTO() throws Exception{
        BookDTO bookDTO = createBookDTO(1L, "Test", true);
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.addNewBookToBookshelf(any(BookDTO.class))).thenReturn(bookshelfDTO);

        mockMvc.perform(post("/bookshelf/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookshelfDTO)));
    }

    @Test
    void addNewBookToBookshelf_shouldNotReturnBookshelfDTO_badRequest() throws Exception{
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.addNewBookToBookshelf(any(BookDTO.class))).thenReturn(bookshelfDTO);

        mockMvc.perform(post("/bookshelf/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBookInBookshelf_shouldReturnBookshelfDTO() throws Exception{
        BookDTO bookDTO = createBookDTO(1L, "Test", true);
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.updateBookInBookshelf(any(BookDTO.class))).thenReturn(List.of(bookDTO));
        when(bookshelfService.generateBookshelfDTO(List.of(bookDTO))).thenReturn(bookshelfDTO);

        mockMvc.perform(put("/bookshelf/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookshelfDTO)));
    }

    @Test
    void updateBookInBookshelf_shouldNotReturnBookshelfDTO_badRequest() throws Exception{
        BookDTO bookDTO = createBookDTO(1L, "Test", true);
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.updateBookInBookshelf(any(BookDTO.class))).thenReturn(List.of(bookDTO));
        when(bookshelfService.generateBookshelfDTO(List.of(bookDTO))).thenReturn(bookshelfDTO);

        mockMvc.perform(put("/bookshelf/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteBookFromBookshelf_shouldReturnBookshelfDTO() throws Exception{
        BookDTO bookDTO = createBookDTO(1L, "Test", true);
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.deleteBookFromBookshelf(any(BookDTO.class))).thenReturn(List.of(bookDTO));
        when(bookshelfService.generateBookshelfDTO(List.of(bookDTO))).thenReturn(bookshelfDTO);

        mockMvc.perform(delete("/bookshelf/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookshelfDTO)));
    }

    @Test
    void deleteBookFromBookshelf_shouldNotReturnBookshelfDTO_badRequest() throws Exception{
        BookDTO bookDTO = createBookDTO(1L, "Test", true);
        BookshelfDTO bookshelfDTO = new BookshelfDTO();
        when(bookshelfService.deleteBookFromBookshelf(any(BookDTO.class))).thenReturn(List.of(bookDTO));
        when(bookshelfService.generateBookshelfDTO(List.of(bookDTO))).thenReturn(bookshelfDTO);

        mockMvc.perform(delete("/bookshelf/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }
}
