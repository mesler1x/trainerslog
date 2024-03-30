package ru.npcric.asparagus.trainerslog.adapter.web.dto.response;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;

import java.util.List;

public record CoachFullResponse (Long id, String name, List<GroupFullResponse> groupDTOList) {
}
