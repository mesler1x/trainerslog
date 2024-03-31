package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupSmallResponse;

import java.util.List;

public record CoachSmallResponse(String name, List<GroupSmallResponse> groups ) {
}
