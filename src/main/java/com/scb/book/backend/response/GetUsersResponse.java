package com.scb.book.backend.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class GetUsersResponse implements Serializable {

    String name;
    String surname;
    String date_of_birth;
    private List<Books> books;

    @Data
    @Builder
    public static class Books  implements Serializable {
        private String id;
        private String book_name;
        private String author_name;
        private BigDecimal price;
        private String is_recommended;
    }


}
