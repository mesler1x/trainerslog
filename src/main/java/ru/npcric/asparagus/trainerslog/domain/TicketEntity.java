package ru.npcric.asparagus.trainerslog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    Long id;
    StudentEntity student;
    LocalDate startDate;
    LocalDate endDate;
    Boolean isExpired;
    Integer paidAmount;
}
