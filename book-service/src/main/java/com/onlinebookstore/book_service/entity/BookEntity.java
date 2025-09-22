package com.onlinebookstore.book_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false )
    private String title;

    @Column(nullable = false)
    private String author;

    private BigDecimal price;

    private String category;

    private String language;

    private String imgUrl;

}
