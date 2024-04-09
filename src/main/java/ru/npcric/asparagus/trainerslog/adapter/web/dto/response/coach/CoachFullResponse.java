package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;

public record CoachFullResponse (Long id, String name, FilialDTO filialDTO, String username) {
}
