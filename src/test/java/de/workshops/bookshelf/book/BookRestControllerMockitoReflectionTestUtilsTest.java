package de.workshops.bookshelf.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class BookRestControllerMockitoReflectionTestUtilsTest {

  @Mock
  private BookService bookService;

  @InjectMocks
  private BookRestController bookRestController;

  @Test
  void getAllBooks() {
    ReflectionTestUtils.setField(bookService, "books", Collections.emptyList());

    assertNotNull(bookRestController.getAllBooks());
    assertEquals(0, bookRestController.getAllBooks().size());
  }
}
