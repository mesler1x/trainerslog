package ru.npcric.asparagus.trainerslog.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    TicketEntity ticket;
    String fullName;
    String sex;
    LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "sgroup_id")
    GroupEntity group;
    int age;
    int q;
    String phoneNumber;
    String parentPhoneNumber;
    String parentFullName;

    @ManyToMany()
    @JoinTable(name = "attendance",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"))
    List<TrainingEntity> trainingEntityList;

    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(name = "email")
    String eemail;

    @Column(name = "balance")
    Integer balance;

    @OneToMany(mappedBy = "student")
    List<ChequeEntity> chequeEntities;

    public StudentEntity(StudentContext context) {
        ticket = context.ticket;
        fullName = context.fullName;
        sex = context.sex;
        birthDate = context.birthDate;
        q = context.q;
        phoneNumber = context.phoneNumber;
        parentPhoneNumber = context.parentPhoneNumber;
        parentFullName = context.parentFullName;
        group = null;
        user = context.user;
        balance = 0;
        chequeEntities = null;
        eemail = context.eemail;
    }

    public record StudentContext(
            TicketEntity ticket,
            String fullName,
            String sex,
            LocalDate birthDate,
            Integer q,
            String phoneNumber,
            String parentPhoneNumber,
            String parentFullName,
            String eemail,
            UserEntity user
    ) {
    }
}
