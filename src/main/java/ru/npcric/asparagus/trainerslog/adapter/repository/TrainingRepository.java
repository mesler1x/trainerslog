package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    TrainingEntity findByDate(LocalDateTime dateTime);

    @Query("SELECT t FROM TrainingEntity t WHERE t.date BETWEEN :startDate AND :endDate")
    List<TrainingEntity> findTrainingsForThePastWeek(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
