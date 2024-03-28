package ru.npcric.asparagus.trainerslog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    List<String> dates;
    String address;
}
