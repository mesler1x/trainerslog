package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
