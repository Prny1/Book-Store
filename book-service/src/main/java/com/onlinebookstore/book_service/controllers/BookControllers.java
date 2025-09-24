package com.onlinebookstore.book_service.controllers;

import com.onlinebookstore.book_service.dto.BookDto;
import com.onlinebookstore.book_service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookControllers {

    private final BookService bookService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @PostMapping
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto){
        BookDto newBook = bookService.addNewBook(bookDto);
        ServiceInstance inventoryService = discoveryClient.getInstances("inventory-service").getFirst();

        restClient.post()
                .uri(inventoryService.getUri() + "/inventory")
                .body(Map.of("bookId", newBook.getId(), "quantity", 1))
                .retrieve()
                .body(Void.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto,
                                              @PathVariable Long id){
        BookDto updateBook = bookService.updateBook(bookDto , id);
        return ResponseEntity.ok(updateBook);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDto> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookDto>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookService.findByCategory(category));
    }




}
