package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String filialName;
    String address;
    @OneToMany(mappedBy = "filial")
    List<CoachEntity> coachesInFilial;
    @ManyToMany
    @JoinTable(name = "TrainingsAndFilials",
    joinColumns = @JoinColumn(name = "filial_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "training_id",referencedColumnName = "id"))
    List<TrainingEntity> training;
}
