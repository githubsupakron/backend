package com.scb.book.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity(name = "m_books")
public class BooksEntity {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "book_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    private String name;

    private String author;

    private BigDecimal price;

    private Boolean is_recommended;

    @ManyToOne
    @JoinColumn(name = "m_user_id", nullable = true)
    private UserEntity user;

}
