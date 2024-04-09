package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, Long> {
    CoachEntity findByUser(UserEntity user);

    CoachEntity findByUser_Username(String username);
}
