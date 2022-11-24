package com.scb.book.backend.service;

import com.scb.book.backend.entity.BooksEntity;
import com.scb.book.backend.entity.UserEntity;
import com.scb.book.backend.exception.BaseException;
import com.scb.book.backend.exception.UserException;
import com.scb.book.backend.repository.UserRepository;
import com.scb.book.backend.response.GetUsersResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GetUsersService {

    private final UserRepository repository;

    public GetUsersService(UserRepository repository) {
        this.repository = repository;
    }

    public List<GetUsersResponse> getUsers() throws BaseException {
        List<GetUsersResponse> responses = new ArrayList<>();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication =context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<UserEntity> opt = repository.findById(Integer.parseInt(userId));
        if (opt.isEmpty()) {
            throw UserException.loginFailUserNotFound();
        }

        UserEntity user = opt.get();
        GetUsersResponse getBooksResponse =  GetUsersResponse.builder().build();
        getBooksResponse.setName(user.getName());
        getBooksResponse.setSurname(user.getUsername());
        getBooksResponse.setDate_of_birth(user.getDate_of_birth());
        List<GetUsersResponse.Books> bookslist = new ArrayList<>();
        user.getBooks().forEach(book ->{
            GetUsersResponse.Books b = GetUsersResponse.Books.builder().build();
            b.setId(book.getId().toString());
            b.setBook_name(book.getName());
            b.setAuthor_name(book.getAuthor());
            b.setPrice(book.getPrice());
            String recommended = Objects.equals(book.getIs_recommended(),true) ?"true" : "false";
            b.setIs_recommended(recommended);
            bookslist.add(b);
        });
        getBooksResponse.setBooks(bookslist);
        responses.add(getBooksResponse);

       /*
        List<UserEntity> userEntityList = (List<UserEntity>) repository.findAll();

        userEntityList.forEach(userEntity ->{

            GetUsersResponse getBooksResponse =  GetUsersResponse.builder().build();
            getBooksResponse.setName(userEntity.getName());
            getBooksResponse.setSurname(userEntity.getUsername());
            getBooksResponse.setDate_of_birth(userEntity.getDate_of_birth());
            List<GetUsersResponse.Books> bookslist = new ArrayList<>();
            userEntity.getBooks().forEach(book ->{
                GetUsersResponse.Books b = GetUsersResponse.Books.builder().build();
                b.setId(book.getId().toString());
                b.setBook_name(book.getName());
                b.setAuthor_name(book.getAuthor());
                b.setPrice(book.getPrice());
                String recommended = Objects.equals(book.getIs_recommended(),true) ?"true" : "false";
                b.setIs_recommended(recommended);
                bookslist.add(b);
            });
            getBooksResponse.setBooks(bookslist);
            responses.add(getBooksResponse);
        });

        */
        return responses;
    }


}
