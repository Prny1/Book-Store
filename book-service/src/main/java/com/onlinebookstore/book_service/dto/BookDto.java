package com.onlinebookstore.book_service.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private String category;
    private String language;
    private String imgUrl;
}
