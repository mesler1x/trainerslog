package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;

import java.util.List;

public record TrainingsForWeekResponse (String groupName, List<TrainingDTO> trainings) {
}
