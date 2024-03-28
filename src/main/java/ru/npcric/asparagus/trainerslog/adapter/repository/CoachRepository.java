package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, Long> {
}
