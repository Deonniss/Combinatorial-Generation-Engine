package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrank.domain.entity.EntityField;
import ru.golovin.springalgrank.domain.entity.Priority;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

    @Query(value = "SELECT * FROM priority ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Priority findRandom();

    @Query("select p from Priority p")
    List<EntityField> findAllObjects();
}
