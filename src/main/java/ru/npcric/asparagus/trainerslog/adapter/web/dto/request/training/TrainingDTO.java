package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TrainingDTO(@NotNull String groupName,
                          @NotNull(message = "start date не может быть null") LocalDateTime date,
                          @NotNull(message = "end date не может быть null") LocalDateTime endDate,
                          String comment) {
}
