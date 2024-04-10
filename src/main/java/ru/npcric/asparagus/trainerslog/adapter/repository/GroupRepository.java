package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String groupName);
}
