package com.scb.book.backend.service;

import com.scb.book.backend.entity.BooksEntity;
import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.exception.UserException;
import com.scb.book.backend.repository.BooksRepository;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.request.PostCreateUserRequest;
import com.scb.book.backend.request.PostUserOrderRequest;
import com.scb.book.backend.response.PostCreateUserResponse;
import com.scb.book.backend.response.PostUsersOrderResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostUsersOrderService {

    private final UserRepository repository;

    private final BooksRepository booksRepository;

    private final PasswordEncoder passwordEncoder;

    public PostUsersOrderService(UserRepository repository, BooksRepository booksRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.booksRepository = booksRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PostUsersOrderResponse execute(PostUserOrderRequest request) throws BaseException{


        PostUsersOrderResponse postCreateUserResponse = PostUsersOrderResponse.builder().build();

        if (Objects.isNull(request)) {
            throw UserException.requestNull();
        }

        if (Objects.isNull(request.getOrders())) {
            throw UserException.createUsernameNull();
        }

        String[] array = request.getOrders().split(",");
        Specification<BooksEntity> specification = Specification.where(
                        getEquals("id", array[0])
                        .or(getEquals("id", array[1]) ));

        List<BooksEntity>  BooksEntityList = booksRepository.findAll(specification);

        BigDecimal sum = BigDecimal.ZERO;
        for (BooksEntity amt : BooksEntityList) {
            sum = sum.add(amt.getPrice());
        }
        System.out.println("Sum = " + sum);

        postCreateUserResponse.setPrice(sum.toString());

        return postCreateUserResponse;
    }

    private Specification<BooksEntity> getEquals(String key, String values) {
        return (root, query, builder) -> builder.equal(root.get(key), values);
    }
}
