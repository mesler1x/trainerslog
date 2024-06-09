package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.ChequeEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ChequeRepository extends JpaRepository<ChequeEntity, Long> {
    ChequeEntity findByDate(LocalDateTime dateTime);
}
