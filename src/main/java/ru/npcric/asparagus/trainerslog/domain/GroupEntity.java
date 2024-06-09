package ru.npcric.asparagus.trainerslog.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sgroup")
public class GroupEntity extends BaseEntity {
    @Column(name = "sgroup_name")
    String groupName;
    @ManyToOne
    CoachEntity coach;
    @OneToMany(mappedBy = "group")
    List<StudentEntity> students;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    List<TrainingEntity> trainingEntities;

    public GroupEntity(GroupContext groupContext) {
        groupName = groupContext.groupName;
        students = groupContext.students;
        coach = groupContext.coach;
        trainingEntities = null;
    }

    public record GroupContext(String groupName, List<StudentEntity> students, CoachEntity coach) {
    }
}
