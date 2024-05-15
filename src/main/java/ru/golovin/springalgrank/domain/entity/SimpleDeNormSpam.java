package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "simple_de_spam")
public class SimpleDeNormSpam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "user_c")
    private String user;
    @Column
    private String event;
    @Column
    private String ip;
    @Column
    private String status;
    @Column
    private String priority;
}


