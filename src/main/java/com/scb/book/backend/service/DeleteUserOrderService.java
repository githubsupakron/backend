package com.scb.book.backend.service;

import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.exception.UserException;
import com.scb.book.backend.repository.BooksRepository;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.request.GetLoginUserRequest;
import com.scb.book.backend.response.GetLoginUserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserOrderService {

    private final UserRepository repository;
    private final BooksRepository booksRepository;
    private final PasswordEncoder passwordEncoder;

    public DeleteUserOrderService(UserRepository repository, BooksRepository booksRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String deleteUser() throws BaseException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication =context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<UserEntity> opt = repository.findById(Integer.parseInt(userId));
        if (opt.isEmpty()) {
            throw UserException.loginFailUserNotFound();
        }

        UserEntity user = opt.get();

        //booksRepository.deleteAll(user.getBooks());
        repository.delete(user);

        return "Test DELETE";
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



}
