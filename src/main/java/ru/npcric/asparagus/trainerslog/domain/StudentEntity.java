package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.time.LocalDate;
import java.util.List;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    public StudentEntity(StudentContext context) {
        ticket = null;
        fullName = context.fullName;
        sex = context.sex;
        birthDate = context.birthDate;
        q = context.q;
        phoneNumber = context.phoneNumber;
        parentPhoneNumber = context.parentPhoneNumber;
        parentFullName = context.parentFullName;
        group = null;
        user = context.user;
    }

    public record StudentContext(
            String fullName,
            String sex,
            LocalDate birthDate,
            Integer q,
            String phoneNumber,
            String parentPhoneNumber,
            String parentFullName,
            UserEntity user
    ) {
    }
}
