package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filial")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String filialName;
    String address;
    @OneToMany(mappedBy = "filial")
    List<CoachEntity> coachesInFilial;
    public FilialEntity(FilialContext context) {
        filialName = context.name;
        address = context.address;
        coachesInFilial = null;
    }
    public record FilialContext(String name, String address) {

    }

}
