package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrank.domain.entity.EntityField;
import ru.golovin.springalgrank.domain.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM event ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Event findRandom();

    @Query("select e from Event e")
    List<EntityField> findAllObjects();
}
