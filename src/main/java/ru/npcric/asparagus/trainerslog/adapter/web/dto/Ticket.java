package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import java.time.LocalDate;

public record Ticket(Student student,
                     LocalDate startDate,
                     LocalDate endDate,
                     Boolean isExpired,
                     Integer paidAmount) {
}
