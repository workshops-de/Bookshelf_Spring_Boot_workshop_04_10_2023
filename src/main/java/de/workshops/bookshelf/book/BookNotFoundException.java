package de.workshops.bookshelf.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Book not found", code = HttpStatus.I_AM_A_TEAPOT)
public class BookNotFoundException extends Exception {

}
