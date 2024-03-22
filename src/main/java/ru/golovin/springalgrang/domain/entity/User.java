package ru.golovin.springalgrang.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Data
@Entity
public class User implements EntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Override
    public String toString() {
        return username;
    }

    @Override
    public String getField() {
        return username;
    }
}
