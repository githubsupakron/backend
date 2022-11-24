package com.scb.book.backend.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "oracle_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

}
