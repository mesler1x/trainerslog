package ru.npcric.asparagus.trainerslog.adapter.web.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record GroupResponse(String groupName, List<LocalDateTime> dates) {
}
