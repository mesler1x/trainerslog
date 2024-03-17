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
    String fullName;
    String sex;
    LocalDate birthDate;
    int age;
    int q;
    String phoneNumber;
    String parentPhoneNumber;
    String parentFullName;
    CoachEntity coach;
    Map<LocalDate, Boolean> visitedStatistics;
}
