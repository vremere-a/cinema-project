package com.dev.cinema.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    private byte[] salt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
