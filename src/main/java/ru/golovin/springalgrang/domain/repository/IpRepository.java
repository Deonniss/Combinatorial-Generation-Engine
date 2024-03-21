package ru.golovin.springalgrang.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrang.domain.entity.Ip;

import java.util.List;

public interface IpRepository extends JpaRepository<Ip, Long> {

    @Query(value = "SELECT * FROM ip ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Ip findRandom();

    @Query("select ip from Ip ip")
    List<Object> findAllObjects();
}
