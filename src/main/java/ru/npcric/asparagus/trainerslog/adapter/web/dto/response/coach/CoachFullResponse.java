package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;

import java.util.List;

public record CoachFullResponse (Long id, String name, List<GroupFullResponse> groupDTOList) {
}
