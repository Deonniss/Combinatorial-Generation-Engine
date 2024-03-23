package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@NoArgsConstructor
@Entity
public class Ip implements EntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String address;

    public Ip(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public String getField() {
        return address;
    }
}
