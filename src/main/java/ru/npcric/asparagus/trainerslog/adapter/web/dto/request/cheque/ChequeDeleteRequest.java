package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChequeDeleteRequest(@NotNull LocalDateTime dateTime) {
}
