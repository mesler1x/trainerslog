package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance;

import java.time.LocalDateTime;

public record GroupAndDateRequest(LocalDateTime dateTime, String groupName) {
}
