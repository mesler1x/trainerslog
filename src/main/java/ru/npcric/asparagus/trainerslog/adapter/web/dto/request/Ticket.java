package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Ticket(@NotNull(message = "Абонемент не может быть создан без студента") Student student,
                     LocalDate startDate,
                     LocalDate endDate,
                     Boolean isExpired,
                     @Min(value = 0, message = "Сумма к оплате не может быть отрицательной") @NotNull Integer paidAmount) {
}
