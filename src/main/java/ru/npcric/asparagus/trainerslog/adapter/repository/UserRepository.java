package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
