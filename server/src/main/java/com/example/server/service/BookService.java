package com.example.server.service;

import com.example.server.model.Book;
import com.example.server.model.BookDto;
import com.example.server.model.Queries;
import com.example.server.repository.BookRepository;
import com.example.server.repository.QueryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private QueryRepository queryRepository;


    public List<Book> findBooksByQuery(String query){
        List<Book> books = bookRepository.searchBooks(query);
        if(books.size() == 0){
            Queries query1 = new Queries();
            query1.setQuery(query);
            queryRepository.save(query1);
        }
        return books;
    }
    public List<Book> getAllBooks( ){
        return bookRepository.findAll();
    }

    public void saveNewBook(BookDto bookDto){
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDto,Book.class);
        bookRepository.save(book);
    }

}
