package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity
public class Event implements EntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    @Override
    public String toString() {
        return type;
    }

    @Override
    public String getField() {
        return type;
    }
}
