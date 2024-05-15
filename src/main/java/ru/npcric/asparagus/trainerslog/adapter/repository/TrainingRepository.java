package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.time.LocalDateTime;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    TrainingEntity findByDate(LocalDateTime dateTime);
}
