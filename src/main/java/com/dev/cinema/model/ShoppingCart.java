package com.dev.cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shopping cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Ticket> tickets;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
