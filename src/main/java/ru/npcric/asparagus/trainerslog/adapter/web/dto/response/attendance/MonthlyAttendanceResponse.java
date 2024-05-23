package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance;

import java.time.LocalDateTime;
import java.util.List;

public record MonthlyAttendanceResponse(List<LocalDateTime> localDateTimeList) {
}
