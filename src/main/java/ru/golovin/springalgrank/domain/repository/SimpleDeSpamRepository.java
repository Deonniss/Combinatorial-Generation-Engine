package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovin.springalgrank.domain.entity.SimpleDeNormSpam;
import ru.golovin.springalgrank.domain.entity.SimpleSpam;

public interface SimpleDeSpamRepository extends JpaRepository<SimpleDeNormSpam, Long> {
}
