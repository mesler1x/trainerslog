package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "training")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingEntity extends BaseEntity {
    LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "sgroup_id")
    GroupEntity group;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "attendance",// посещяемость
            joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    List<StudentEntity> studentEntityList;
}
