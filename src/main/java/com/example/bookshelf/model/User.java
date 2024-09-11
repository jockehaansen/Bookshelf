package com.example.bookshelf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue()
    private UUID id;

    private String email;
    private String password;

    @OneToMany
    private List<Book> books;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
