package com.onlinebookstore.book_service.service;

import com.onlinebookstore.book_service.dto.BookDto;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService {

    public BookDto addNewBook(BookDto bookDto);

    public BookDto updateBook(BookDto bookDto , Long id);

    public List<BookDto> getAllBook();

    public BookDto findByTitle(String title);

    public List<BookDto> findByCategory(String category);
}
