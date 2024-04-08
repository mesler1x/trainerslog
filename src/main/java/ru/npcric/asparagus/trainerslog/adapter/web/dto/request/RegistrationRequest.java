package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

import java.util.List;

public record RegistrationRequest(@NotNull @NotBlank String username,
                                  @NotNull @NotBlank String password) {
}
