package de.workshops.bookshelf.book;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
@AutoConfigureMockMvc
class BookRestControllerMockitoAndRestAssuredIntegrationTest {

  @Autowired
  private BookRestController bookRestController;

  @MockBean
  private BookService bookService;

  @Test
  void testWithMockitoAndRestAssuredMockMvc() {
    Book book = new Book();
    book.setAuthor("Test");

    Mockito.when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));

    RestAssuredMockMvc.standaloneSetup(bookRestController);
    RestAssuredMockMvc.
        given()
        .log().all().
        when()
        .get("/book").
        then()
        .log().all()
        .statusCode(200)
        .body("author[0]", equalTo("Test"));
  }
}
