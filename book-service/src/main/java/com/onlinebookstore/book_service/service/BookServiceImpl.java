package com.onlinebookstore.book_service.service;

import com.onlinebookstore.book_service.BookServiceApplication;
import com.onlinebookstore.book_service.dto.BookDto;
import com.onlinebookstore.book_service.entity.BookEntity;
import com.onlinebookstore.book_service.exceptions.ResourceNotFoundException;
import com.onlinebookstore.book_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookDto addNewBook(BookDto bookDto) {
        BookEntity entity = modelMapper.map(bookDto , BookEntity.class);
        BookEntity savedBook = bookRepository.save(entity);

        return modelMapper.map(savedBook , BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto bookDto , Long id) {

        BookEntity book = bookRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Book not present with id" + id));

        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setPrice(bookDto.getPrice());
        book.setLanguage(bookDto.getLanguage());
        book.setImgUrl(bookDto.getImgUrl());
        book.setTitle(bookDto.getTitle());

        return modelMapper.map(book , BookDto.class);
    }

    @Override
    public List<BookDto> getAllBook() {

        List<BookEntity> listOfAllBook = bookRepository.findAll();

        List<BookDto> bookDtoList = listOfAllBook.stream()
                .map(books -> modelMapper.map(books , BookDto.class))
                .collect(Collectors.toList());

        return bookDtoList ;
    }

    @Override
    public BookDto findByTitle(String title) {
        BookEntity book = bookRepository.findByTitle(title);
        return modelMapper.map(book, BookDto.class);
    }


    @Override
    public List<BookDto> findByCategory(String category) {

        List<BookEntity> book = bookRepository.findByCategory(category);
        return book.stream()
                .map(books -> modelMapper.map(books , BookDto.class))
                .collect(Collectors.toList());
    }
}
