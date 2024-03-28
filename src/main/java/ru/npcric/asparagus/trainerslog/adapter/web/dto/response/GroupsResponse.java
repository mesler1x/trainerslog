package ru.npcric.asparagus.trainerslog.adapter.web.dto.response;

import java.time.LocalDate;
import java.util.List;

public record GroupsResponse(String groupName, List<LocalDate> dates) {
}
