package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

import java.util.List;

public record RegistrationRequest(String login,
                                  String password) {
}
