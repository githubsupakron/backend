package com.scb.book.backend.controller;

import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.request.GetLoginUserRequest;
import com.scb.book.backend.request.PostCreateUserRequest;
import com.scb.book.backend.request.PostUserOrderRequest;
import com.scb.book.backend.response.GetLoginUserResponse;
import com.scb.book.backend.response.GetUsersResponse;
import com.scb.book.backend.response.PostCreateUserResponse;
import com.scb.book.backend.response.PostUsersOrderResponse;
import com.scb.book.backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final GetLoginUserService getLoginBookService;
    private final PostCreateUserService postCreateUserService;

    private final GetUsersService getUsersService;

    private final PostUsersOrderService postUsersOrderService;
    private final DeleteUserOrderService deleteUserOrderService;

    public UserController(GetLoginUserService getLoginBookService, PostCreateUserService postCreateUserService, GetUsersService getUsersService, PostUsersOrderService postUsersOrderService, DeleteUserOrderService deleteUserOrderService) {
        this.getLoginBookService = getLoginBookService;
        this.postCreateUserService = postCreateUserService;
        this.getUsersService = getUsersService;
        this.postUsersOrderService = postUsersOrderService;
        this.deleteUserOrderService = deleteUserOrderService;
    }

    // GET LOGIN
    @PostMapping(path = "/login")
    public ResponseEntity<GetLoginUserResponse> PostLoginBook(@RequestBody GetLoginUserRequest request) throws BaseException {

        GetLoginUserResponse response = getLoginBookService.login(request);
        return ResponseEntity.ok(response);

    }

    // POST CREATE
    @PostMapping(path = "/create-user")
    public ResponseEntity<PostCreateUserResponse> PostCreateUser(@RequestBody PostCreateUserRequest request) throws BaseException {

        PostCreateUserResponse response = postCreateUserService.execute(request);
        return ResponseEntity.ok(response);

    }

    // get users
    @GetMapping(path = "/users")
    public ResponseEntity<List<GetUsersResponse>> GetUsers() throws BaseException {

        List<GetUsersResponse> response = getUsersService.getUsers();
        return ResponseEntity.ok(response);

    }

    // DELETE /users
    @DeleteMapping(path = "/users")
    public String DeleteUserOrder() throws BaseException {

        String response = deleteUserOrderService.deleteUser();
        return response;

    }

    // POST /users/orders
    @PostMapping(path = "/users/orders")
    public ResponseEntity<PostUsersOrderResponse> PostUserOrder(@RequestBody PostUserOrderRequest request) throws BaseException {

        PostUsersOrderResponse response = postUsersOrderService.execute(request);
        return ResponseEntity.ok(response);

    }

}