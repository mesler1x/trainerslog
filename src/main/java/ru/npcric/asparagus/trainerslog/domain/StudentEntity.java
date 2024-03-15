package ru.npcric.asparagus.trainerslog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    Long id;
    TicketEntity ticket;
    String name;
    CoachEntity coach;
    Map<LocalDate, Boolean> visitedStatistics;
}
