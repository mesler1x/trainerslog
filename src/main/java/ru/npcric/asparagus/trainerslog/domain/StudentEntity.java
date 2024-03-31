package ru.npcric.asparagus.trainerslog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    TicketEntity ticket;
    String fullName;
    String sex;
    LocalDate birthDate;
    @ManyToOne
    GroupEntity group;
    int age;
    int q;
    String phoneNumber;
    String parentPhoneNumber;
    String parentFullName;
    @ManyToMany
    @JoinTable(name = "attendance",
    joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"))
    List<TrainingEntity> trainingEntityList;
}
