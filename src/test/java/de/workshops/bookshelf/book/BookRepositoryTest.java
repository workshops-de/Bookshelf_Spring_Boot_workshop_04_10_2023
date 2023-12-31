package de.workshops.bookshelf.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void createBook() {
        String isbn = "123-4567890";
        Book book = buildAndSaveBook(isbn);

        bookRepository.save(book);
        List<Book> books = bookRepository.findAll();

        assertNotNull(books);
        assertEquals(4, books.size());
        assertEquals(book.getTitle(), books.get(3).getTitle());

        bookRepository.delete(book);
        books = bookRepository.findAll();

        assertNotNull(books);
        assertEquals(3, books.size());
    }

    @Test
    void findBookByIsbn() {
        String isbn = "123-4567890";
        Book book = buildAndSaveBook(isbn);

        Book newBook = bookRepository.findByIsbn(isbn);

        assertNotNull(newBook);
        assertEquals(book.getTitle(), newBook.getTitle());
    }

    private Book buildAndSaveBook(String isbn) {
        Book book = Book.builder()
            .title("Title")
            .author("Author")
            .description("Description")
            .isbn(isbn)
            .build();

        return bookRepository.save(book);
    }
}
