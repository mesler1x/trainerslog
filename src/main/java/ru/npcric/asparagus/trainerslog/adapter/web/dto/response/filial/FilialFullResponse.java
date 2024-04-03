package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.filial;

import java.util.List;

public record FilialFullResponse(Long id, String filialName, String address, List<String> coachesNames) {
}
