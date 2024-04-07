package ru.npcric.asparagus.trainerslog.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    public FilialEntity(FilialContext context) {
        filialName = context.name;
        address = context.address;
        coachesInFilial = null;
        training = null;
    }
    public record FilialContext(String name, String address) {

    }

}
