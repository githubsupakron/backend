package com.scb.book.backend.repository;

import com.scb.book.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
