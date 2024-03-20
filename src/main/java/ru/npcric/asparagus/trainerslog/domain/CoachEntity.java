package ru.npcric.asparagus.trainerslog.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachEntity {
    Long id;
    String name;
    List<GroupEntity> groups;
}
