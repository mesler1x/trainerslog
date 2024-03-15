package ru.npcric.asparagus.trainerslog.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupEntity {
    Long id;
    String groupName;
    List<StudentEntity> students;
    List<LocalDate> dates;
}
