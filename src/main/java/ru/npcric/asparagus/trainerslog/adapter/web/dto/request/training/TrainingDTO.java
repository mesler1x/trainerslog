package ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TrainingDTO(LocalDateTime date, List<StudentDTO> studentDTOList) {
}
