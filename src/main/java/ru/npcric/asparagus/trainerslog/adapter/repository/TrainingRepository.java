package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;
@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
}
