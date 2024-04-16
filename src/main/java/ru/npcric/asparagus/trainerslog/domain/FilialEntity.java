package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.npcric.asparagus.trainerslog.domain.common.BaseEntity;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filial")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilialEntity extends BaseEntity {
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
