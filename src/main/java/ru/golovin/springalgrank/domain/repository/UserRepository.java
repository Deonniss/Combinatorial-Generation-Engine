package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.golovin.springalgrank.domain.entity.EntityField;
import ru.golovin.springalgrank.domain.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    User findRandom();

    @Query("select u from User u")
    List<EntityField> findAllObjects();
}
