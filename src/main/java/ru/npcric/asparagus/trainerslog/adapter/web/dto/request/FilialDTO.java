package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FilialDTO(@NotBlank String name,
                        @NotBlank String address,
                        List<CoachDTO> coachDTOList) {
}
