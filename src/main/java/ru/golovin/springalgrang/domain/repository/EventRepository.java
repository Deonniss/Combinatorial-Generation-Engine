package ru.golovin.springalgrang.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrang.domain.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM event ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Event findRandom();
}
