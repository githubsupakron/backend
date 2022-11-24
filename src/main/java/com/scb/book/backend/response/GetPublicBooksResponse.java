package com.scb.book.backend.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class GetPublicBooksResponse implements Serializable {

    private String id;
    private String book_name;
    private String author_name;
    private BigDecimal price;

}
