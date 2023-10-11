package de.workshops.bookshelf.book;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BookService {

  private final BookRepository bookRepository;

  private List<Book> books;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  Book searchBookByIsbn(String isbn) throws BookNotFoundException {
    final var book = bookRepository.findByIsbn(isbn);
    if (book == null) {
      throw new BookNotFoundException();
    }

    return book;
  }

  Book searchBookByAuthor(String author) throws BookNotFoundException {
    return getAllBooks().stream().filter(book -> hasAuthor(book, author)).findFirst()
        .orElseThrow(BookNotFoundException::new);
  }

  List<Book> searchBooks(BookSearchRequest request) {
    return getAllBooks().stream()
        .filter(book -> hasAuthor(book, request.author()))
        .filter(book -> hasIsbn(book, request.isbn()))
        .toList();
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  private boolean hasIsbn(Book book, String isbn) {
    return book.getIsbn().equals(isbn);
  }

  private boolean hasAuthor(Book book, String author) {
    return book.getAuthor().contains(author);
  }
}
