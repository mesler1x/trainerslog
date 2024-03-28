package ru.npcric.asparagus.trainerslog.adapter.web.dto.response;

import java.util.List;

public record GroupsResponse(String groupName, List<String> dates) {
}
