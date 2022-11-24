package com.scb.book.backend.repository;

import com.scb.book.backend.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface BooksRepository extends CrudRepository<BooksEntity, Integer> , JpaSpecificationExecutor<BooksEntity> {



}
