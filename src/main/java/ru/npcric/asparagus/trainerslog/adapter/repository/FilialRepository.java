package ru.npcric.asparagus.trainerslog.adapter.repository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
@Repository
public interface FilialRepository extends JpaRepository<FilialEntity, Long> {
    FilialEntity findByAddress(@NotBlank String address);
    void deleteByAddress(String address);

    @Transactional
    @Modifying
    @Query("UPDATE CoachEntity ch SET ch.filial = null WHERE ch.filial.id = :filialId")
    void updateCoachesSetFilialToNull(@Param("filialId") Long filialId);
}
