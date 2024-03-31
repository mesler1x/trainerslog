package ru.npcric.asparagus.trainerslog.domain;

import jakarta.persistence.*;
import lombok.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CoachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne
    FilialEntity filial;
    @OneToMany(mappedBy = "coach")
    List<GroupEntity> groups;

}
