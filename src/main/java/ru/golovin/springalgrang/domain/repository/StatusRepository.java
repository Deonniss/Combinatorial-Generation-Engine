package ru.golovin.springalgrang.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrang.domain.entity.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(value = "SELECT * FROM status ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Status findRandom();

    @Query("select s from Status s")
    List<Object> findAllObjects();
}
