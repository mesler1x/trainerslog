package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student;

import java.util.List;

public record StudentsInGroupResponse(String groupName, String coachName, List<String> students) {
}
