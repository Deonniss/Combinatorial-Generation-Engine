package ru.golovin.springalgrang.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrang.domain.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

    @Query(value = "SELECT * FROM priority ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Priority findRandom();
}
