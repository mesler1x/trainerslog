package ru.npcric.asparagus.trainerslog.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoachEntity {
    Long id;
    String name;
    List<GroupEntity> groups;
}
