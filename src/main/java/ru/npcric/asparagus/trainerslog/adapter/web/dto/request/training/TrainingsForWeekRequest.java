package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TrainingsForWeekRequest (@NotNull String groupName, @NotNull LocalDateTime mondayDate) {
}
