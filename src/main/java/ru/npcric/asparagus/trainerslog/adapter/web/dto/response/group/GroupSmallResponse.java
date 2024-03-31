package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group;

import java.time.LocalDate;
import java.util.List;

public record GroupSmallResponse(String groupName, List<LocalDate> dates) {
}
