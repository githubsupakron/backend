package com.scb.book.backend;

import com.scb.book.backend.entity.BooksEntity;
import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.repository.BooksRepository;
import com.scb.book.backend.request.PostCreateUserRequest;
import com.scb.book.backend.request.PostUserOrderRequest;
import com.scb.book.backend.response.PostUsersOrderResponse;
import com.scb.book.backend.service.PostCreateUserService;
import com.scb.book.backend.service.PostUsersOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostUserOrderServiceTest {

    @InjectMocks
    private PostUsersOrderService postUsersOrderService;

    @Mock
    private  BooksRepository booksRepository;

    @Test
    void testGetUserOrder_success() throws BaseException {

        List<BooksEntity> booksEntityList = new ArrayList<>();
        BooksEntity boksEntity = new BooksEntity();
        boksEntity.setId(1);
        boksEntity.setName("N1");
        boksEntity.setPrice(BigDecimal.valueOf(200.00));
        boksEntity.setIs_recommended(true);
        boksEntity.setAuthor("A0");

        BooksEntity boksEntity2 = new BooksEntity();
        boksEntity2.setId(1);
        boksEntity2.setName("N1");
        boksEntity2.setPrice(BigDecimal.valueOf(200.00));
        boksEntity2.setIs_recommended(true);
        boksEntity2.setAuthor("A0");
        booksEntityList.add(boksEntity);
        booksEntityList.add(boksEntity2);

        PostUserOrderRequest postUserOrderRequest = new PostUserOrderRequest();
        postUserOrderRequest.setOrders("1,2");

        when(booksRepository.findAll(any())).thenReturn(booksEntityList);

        PostUsersOrderResponse response = postUsersOrderService.execute(postUserOrderRequest);

        Assertions.assertEquals(response.getPrice(),"400.0");
    }


}
