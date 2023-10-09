package de.workshops.bookshelf.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BookRestControllerMockitoTest {

    @Autowired
    private BookRestController bookRestController;

    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks() {
        Mockito.when(bookService.getAllBooks()).thenReturn(Collections.emptyList());

        assertNotNull(bookRestController.getAllBooks());
        assertEquals(0, bookRestController.getAllBooks().size());
    }
}
