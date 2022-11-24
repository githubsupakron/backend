package com.scb.book.backend.request;

import lombok.Data;

@Data
public class GetLoginUserRequest {

    String username;
    String password;

}
