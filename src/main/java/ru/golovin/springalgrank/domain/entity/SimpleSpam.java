package ru.golovin.springalgrank.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class SimpleSpam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ip_id", nullable = false)
    private Ip ip;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;
}

