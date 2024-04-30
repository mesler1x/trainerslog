package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth;

import java.util.List;

public record RegistrationResponse(String username, String encryptedPassword, List<String> role) {
}
