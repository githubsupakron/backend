package com.scb.book.backend;

import com.scb.book.backend.entity.BooksEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.repository.BooksRepository;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.request.PostCreateUserRequest;
import com.scb.book.backend.request.PostUserOrderRequest;
import com.scb.book.backend.response.PostCreateUserResponse;
import com.scb.book.backend.response.PostUsersOrderResponse;
import com.scb.book.backend.service.PostCreateUserService;
import com.scb.book.backend.service.PostUsersOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostCreateUserServiceTest {

    @InjectMocks
    private PostCreateUserService postCreateUserService;
    @Mock
    private  BooksRepository booksRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  UserRepository repository;


    @Test
    void testGetUserOrder_success() throws BaseException {

        PostCreateUserRequest.Books books1= new PostCreateUserRequest.Books();
        books1.setBook_name("name");
        books1.setAuthor_name("Author");
        books1.setPrice(BigDecimal.valueOf(100.00));
        books1.setIs_recommended("Y");

        PostCreateUserRequest.Books books2= new PostCreateUserRequest.Books();
        books2.setBook_name("name2");
        books2.setAuthor_name("Author2");
        books2.setPrice(BigDecimal.valueOf(200.00));
        books2.setIs_recommended("N");

        List<PostCreateUserRequest.Books> booksList = new ArrayList<>();
        booksList.add(books1);
        booksList.add(books2);

        PostCreateUserRequest postCreateUserRequest = new PostCreateUserRequest();
        postCreateUserRequest.setUsername("user");
        postCreateUserRequest.setPassword("pass");
        postCreateUserRequest.setSurname("Surname");
        postCreateUserRequest.setName("name");
        postCreateUserRequest.setDate_of_birth("11/11/2022");
        postCreateUserRequest.setBooks(booksList);

        PostCreateUserResponse postCreateUserResponse = postCreateUserService.execute(postCreateUserRequest);
        Assertions.assertEquals(postCreateUserResponse.getMessage(),"SUCCESS");
    }


}
