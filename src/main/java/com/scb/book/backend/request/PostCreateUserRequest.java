package com.scb.book.backend.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PostCreateUserRequest {

    private String username;
    private String password;
    private String date_of_birth;
    private String name;
    private String surname;
    private List<Books> books;

    @Data
    public static class Books  {
        private String book_name;
        private String author_name;
        private BigDecimal price;
        private String is_recommended;
    }
}
