package ru.npcric.asparagus.trainerslog.adapter.web.dto.response;

import java.util.List;

public record CoachSmallResponse(String name, List<GroupsResponse> groups ) {
}
