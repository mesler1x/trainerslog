package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
