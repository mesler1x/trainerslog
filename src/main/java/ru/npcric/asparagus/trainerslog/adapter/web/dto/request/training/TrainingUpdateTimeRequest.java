package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TrainingUpdateTimeRequest (@NotNull LocalDateTime originalStartDate, LocalDateTime newStartDate, LocalDateTime newEndDate) {
}
