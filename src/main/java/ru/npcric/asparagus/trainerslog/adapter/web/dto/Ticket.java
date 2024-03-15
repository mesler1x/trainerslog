package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record Ticket(@NotNull(message = "Абонемент не может быть создан без студента") Student student,
                     LocalDate startDate,
                     LocalDate endDate,
                     Boolean isExpired,
                     @Positive(message = "Сумма к оплате не может быть отрицательной") @NotNull Integer paidAmount) {
}
