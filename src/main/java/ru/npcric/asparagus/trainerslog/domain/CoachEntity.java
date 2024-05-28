package ru.npcric.asparagus.trainerslog.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coach")
public class CoachEntity extends BaseEntity {
    String name;
    @Column(name = "email")
    String eemail;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "sex")
    String sex;
    @Column(name = "birth_date")
    LocalDate birthDate;
    @ManyToOne
    FilialEntity filial;
    @OneToMany(mappedBy = "coach")
    List<GroupEntity> groups;
    @OneToOne
    UserEntity user;

    public CoachEntity(CoachContext coachContext) {
        name = coachContext.name;
        eemail = coachContext.eemail;
        phoneNumber = coachContext.phoneNumber;
        sex  = coachContext.sex;
        birthDate = coachContext.birthDate;
        filial = coachContext.filial;
        user = coachContext.user;
        groups = null;
    }

    public record CoachContext(String name, String eemail, String phoneNumber,
                               String sex, LocalDate birthDate, FilialEntity filial, UserEntity user) {

    }
}
