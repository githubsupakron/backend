package com.scb.book.backend.service;

import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.exception.UserException;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.request.GetLoginUserRequest;
import com.scb.book.backend.response.GetLoginUserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetLoginUserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public GetLoginUserService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public GetLoginUserResponse login(GetLoginUserRequest request) throws BaseException {
        // validate request
        GetLoginUserResponse getLoginUserResponse =  GetLoginUserResponse.builder().build();
        // verify database
        Optional<UserEntity> opt = repository.findByUsername(request.getUsername());
        if (opt.isEmpty()) {
            throw UserException.loginFailUserNotFound();
        }

        UserEntity user = opt.get();
        if (!matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        String token = tokenService.tokenize(user);

        getLoginUserResponse.setMessageToken(token);
        return getLoginUserResponse;
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



}
