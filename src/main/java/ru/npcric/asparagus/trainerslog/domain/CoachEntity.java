package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class CoachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne
    FilialEntity filial;
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    List<GroupEntity> groups;
    @OneToOne
    @JoinColumn(name = "user_id")
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
