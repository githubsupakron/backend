package com.scb.book.backend.controller;

import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.response.GetPublicBooksResponse;
import com.scb.book.backend.response.GetUsersResponse;
import com.scb.book.backend.service.GetPublicBooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class PublicBooksController {

    private final GetPublicBooksService getPublicBooksService;

    public PublicBooksController(GetPublicBooksService getPublicBooksService) {
        this.getPublicBooksService = getPublicBooksService;
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<GetPublicBooksResponse>> GetPublicBooks() throws BaseException, IOException, InterruptedException {

        List<GetPublicBooksResponse> response = getPublicBooksService.getPublicBooks();
        return ResponseEntity.ok(response);

    }
}
