package ru.npcric.asparagus.trainerslog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;

import java.time.LocalDate;
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
    List<LocalDate> dates;

    public GroupEntity(String name, List<StudentEntity> students, List<LocalDate> dates){
        this.groupName = name;
        this.students = students;
        this.dates = dates;
    }

}
