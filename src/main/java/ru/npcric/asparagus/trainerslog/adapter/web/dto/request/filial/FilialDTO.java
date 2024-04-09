package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial;

import jakarta.validation.constraints.NotBlank;

public record FilialDTO(@NotBlank String name,
                        @NotBlank String address) {
}
