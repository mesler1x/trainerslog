package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TrainingDTO(@NotNull String groupName,
                          @NotNull LocalDateTime date,
                          @NotNull LocalDateTime endDate,
                          String comment) {
}
