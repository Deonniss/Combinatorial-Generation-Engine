package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovin.springalgrank.domain.entity.SimpleSpam;

public interface SimpleSpamRepository extends JpaRepository<SimpleSpam, Long> {
}
