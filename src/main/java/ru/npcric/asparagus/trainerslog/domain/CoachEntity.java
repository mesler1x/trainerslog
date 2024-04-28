package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coach")
public class CoachEntity extends BaseEntity {

    String name;
    @ManyToOne
    FilialEntity filial;
    @OneToMany(mappedBy = "coach")
    List<GroupEntity> groups;
    @OneToOne
    UserEntity user;

    public CoachEntity(CoachContext coachContext) {
        name = coachContext.name;
        filial = coachContext.filial;
        user = coachContext.user;
        groups = null;
    }

    public record CoachContext(String name, FilialEntity filial, UserEntity user) {

    }
}
