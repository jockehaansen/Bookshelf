package com.example.bookshelf;

import com.example.bookshelf.controller.BooksController;
import com.example.bookshelf.dto.SaveBookDTO;
import com.example.bookshelf.service.BooksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.bookshelf.testUtilities.TestDataFactory.createSaveBookDTO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
class BooksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService booksService;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init(){
        assertNotNull(booksService);

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BooksController(booksService)).build();
    }

    @Test
    void saveBooksFromGoogleBooksTest_shouldRespondSuccess() throws Exception {
        SaveBookDTO saveBookDTO = createSaveBookDTO("Test");

        doNothing().when(booksService).saveBookFromGoogleBooks(any(SaveBookDTO.class));

        mockMvc.perform(post("/books/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saveBookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Book saved successfully in Bookshelf"));
    }

    @Test
    void saveBooksFromGoogleBooks_shouldNotRespondSuccess() throws Exception {
        doNothing().when(booksService).saveBookFromGoogleBooks(any(SaveBookDTO.class));

        mockMvc.perform(post("/books/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }
}
