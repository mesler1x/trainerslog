package ru.npcric.asparagus.trainerslog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group")
public class GroupEntity extends BaseEntity {
    String groupName;
    @ManyToOne
    CoachEntity coach;
    @OneToMany(mappedBy = "group")
    List<StudentEntity> students;
    @OneToMany(mappedBy = "group")
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
