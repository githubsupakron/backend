package com.scb.book.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(length = 120)
    private String name;

    @Column(length = 60)
    private String surname;

    @Column(length = 60)
    private String date_of_birth;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BooksEntity> books;

}
