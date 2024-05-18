package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training;

import java.time.LocalDateTime;

public record TrainingCreateResponse (Long id, String groupName, LocalDateTime dateTime) {
}
