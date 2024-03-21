package ru.golovin.springalgrang.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrang.domain.entity.Ip;

public interface IpRepository extends JpaRepository<Ip, Long> {

    @Query(value = "SELECT * FROM ip ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Ip findRandom();
}