package ru.golovin.springalgrang.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;
}
