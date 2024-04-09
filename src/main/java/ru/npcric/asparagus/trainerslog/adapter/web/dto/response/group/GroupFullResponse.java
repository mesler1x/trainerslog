package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group;

import java.time.LocalDateTime;
import java.util.List;

public record GroupFullResponse(Long id,
                                String groupName,
                                List<String> studentNames,
                                List<LocalDateTime> dates) {
}
