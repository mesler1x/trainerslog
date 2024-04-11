package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import java.time.LocalDateTime;

public record TrainingDTO(String groupName, LocalDateTime dates) {
}
