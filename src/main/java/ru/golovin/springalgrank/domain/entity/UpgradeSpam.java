package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Table
@Data
@Entity
public class UpgradeSpam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private BigInteger rank;
}
