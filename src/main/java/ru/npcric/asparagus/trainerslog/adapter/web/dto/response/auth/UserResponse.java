package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth;

import java.util.List;

public record UserResponse(String name, List<String> role) {
}
