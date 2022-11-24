package com.scb.book.backend.service;

import com.scb.book.backend.entity.BooksEntity;
import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.exception.UserException;
import com.scb.book.backend.repository.BooksRepository;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.request.PostCreateUserRequest;
import com.scb.book.backend.response.PostCreateUserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PostCreateUserService {

    private final UserRepository repository;

    private final BooksRepository booksRepository;

    private final PasswordEncoder passwordEncoder;

    public PostCreateUserService(UserRepository repository, BooksRepository booksRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PostCreateUserResponse execute(PostCreateUserRequest request) throws BaseException{


        PostCreateUserResponse postCreateUserResponse = PostCreateUserResponse.builder().build();

        if (Objects.isNull(request)) {
            throw UserException.requestNull();
        }

        if (Objects.isNull(request.getUsername())) {
            throw UserException.createUsernameNull();
        }

        if (Objects.isNull(request.getPassword())) {
            throw UserException.createPasswordNull();
        }

        if (Objects.isNull(request.getName())) {
            throw UserException.createNameNull();
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw UserException.createUserDuplicated();
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setDate_of_birth(request.getDate_of_birth());
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        repository.save(entity);

        request.getBooks().forEach(book ->{
            BooksEntity booksEntity = new BooksEntity();
            booksEntity.setUser(entity);
            booksEntity.setName(book.getBook_name());
            booksEntity.setAuthor(book.getAuthor_name());
            booksEntity.setPrice(book.getPrice());
            Boolean isRecommended = Objects.equals(book.getIs_recommended(),"Y") ? true : false;
            booksEntity.setIs_recommended(isRecommended);
            booksRepository.save(booksEntity);
        });


        postCreateUserResponse.setMessage("SUCCESS");

        return postCreateUserResponse;
    }
}
