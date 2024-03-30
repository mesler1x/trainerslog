package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "training")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime date;
    @ManyToMany
    @JoinTable(name = "TrainingsAndFilials",
    joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "filial_id", referencedColumnName = "id"))
    List<FilialEntity> filial;
    @ManyToMany
    @JoinTable(name = "attendance",// посещяемость
            joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    List<StudentEntity> studentEntityList;
}
