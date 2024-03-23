package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity
public class Priority implements EntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getField() {
        return name;
    }
}