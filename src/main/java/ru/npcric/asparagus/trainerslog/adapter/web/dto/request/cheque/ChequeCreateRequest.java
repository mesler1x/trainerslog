package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque;

import jakarta.validation.constraints.NotNull;

public record ChequeCreateRequest(@NotNull String link) {
}
