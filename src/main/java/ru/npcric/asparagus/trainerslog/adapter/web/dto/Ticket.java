package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record Ticket(@NotNull Student student,
                     LocalDate startDate,
                     LocalDate endDate,
                     Boolean isExpired,
                     @Positive @NotNull Integer paidAmount) {
}
