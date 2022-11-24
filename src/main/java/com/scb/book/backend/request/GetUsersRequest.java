package com.scb.book.backend.request;

import lombok.Data;

@Data
public class GetUsersRequest {

    String username;
    String password;

}
