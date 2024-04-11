package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;

import java.util.List;

public record CoachStudentsResponse(String coachName, List<StudentWithGroupSmallResponse> student) {
}
