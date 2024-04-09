package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequest(@NotNull @NotBlank String username,
                                  @NotNull @NotBlank String password) {
}
