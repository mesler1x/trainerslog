package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
@Repository
public interface FilialRepository extends JpaRepository<FilialEntity, Long> {
    FilialEntity findByAddress(@NotBlank String address);
}
