package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrank.domain.entity.EntityField;
import ru.golovin.springalgrank.domain.entity.Ip;

import java.util.List;

public interface IpRepository extends JpaRepository<Ip, Long> {

    @Query(value = "SELECT * FROM ip ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Ip findRandom();

    @Query("select ip from Ip ip")
    List<EntityField> findAllObjects();
}
