package com.dev.cinema.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @NonNull
    @Column(unique = true, name = "user_email")
    private String email;
    @NotNull
    @Column(name = "user_password")
    private String password;

    @ManyToMany
    private Set<Role> userRole;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
