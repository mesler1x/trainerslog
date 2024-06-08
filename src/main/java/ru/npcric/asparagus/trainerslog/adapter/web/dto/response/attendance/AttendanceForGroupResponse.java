package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AttendanceForGroupResponse(LocalDateTime TrainingDate, List<String> studentNames) {
}
