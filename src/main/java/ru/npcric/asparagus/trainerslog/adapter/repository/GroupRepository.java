package ru.npcric.asparagus.trainerslog.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String groupName);
    //void deleteGroupIn(List<GroupEntity> groupEntityList);

    @Modifying
    @Query("UPDATE StudentEntity s SET s.group = null WHERE s.group.id = :groupId")
    void updateStudentsSetGroupToNull(@Param("groupId") Long groupId);

    void deleteByGroupName(String groupName);
}
