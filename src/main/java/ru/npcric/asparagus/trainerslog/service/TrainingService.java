package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrainingService {
    TrainingRepository trainingRepository;
    // todo - тренер начинает тренировку и отмечает учеников по именам
}
