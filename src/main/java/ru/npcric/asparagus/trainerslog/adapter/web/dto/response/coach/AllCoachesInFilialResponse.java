package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import java.util.List;

public record AllCoachesInFilialResponse(String filialName, List<String> coachesNames) {
}
