package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChequeConfirmRequest(@NotNull LocalDateTime dateTime, @NotNull Integer amount, @NotNull String link) {
}
