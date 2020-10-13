package com.dev.cinema.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Ticket> tickets;
    private LocalDateTime orderDate;
    @ManyToOne
    private User user;
}
