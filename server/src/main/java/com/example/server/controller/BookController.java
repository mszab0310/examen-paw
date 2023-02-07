package com.example.server.controller;

import com.example.server.model.Book;
import com.example.server.model.BookDto;
import com.example.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    List<Book> getBookByQuery(@RequestParam String query) {
        return bookService.findBooksByQuery(query);
    }

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value="/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewBook(@RequestBody BookDto bookDto){
        bookService.saveNewBook(bookDto);
    }
}
