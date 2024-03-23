package ru.golovin.springalgrank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.golovin.springalgrank.domain.entity.UpgradeSpam;

public interface UpgradeSpamRepository extends JpaRepository<UpgradeSpam, Long> {
}
