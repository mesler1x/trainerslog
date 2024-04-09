package ru.npcric.asparagus.trainerslog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String groupName;
    @JsonIgnore
    @ManyToOne
    CoachEntity coach;
    @OneToMany(mappedBy = "group")
    List<StudentEntity> students;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    List<LocalDateTime> dates;


    public GroupEntity(GroupContext groupContext) {
        groupName = groupContext.groupName;
        students = groupContext.students;
        dates = groupContext.dates;
        coach = groupContext.coach;
    }
    public record GroupContext(String groupName, List<StudentEntity> students, List<LocalDateTime> dates, CoachEntity coach) {
    }
}
