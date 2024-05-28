package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TrainingUpdateCommentRequest(@NotNull LocalDateTime startDate, @Nullable String comment) {
}
