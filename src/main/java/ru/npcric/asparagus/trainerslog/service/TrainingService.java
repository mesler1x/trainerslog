package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrainingService {
    TrainingRepository trainingRepository;
    // todo - тренер начинает тренировку и отмечает учеников по именам

    public void deleteTraining(Long id){
        Optional<TrainingEntity> trainingEntityOptional = trainingRepository.findById(id);
        TrainingEntity trainingEntity = trainingEntityOptional.get();//обработать ошибку
        List<StudentEntity> studentEntityList =  trainingEntity.getStudentEntityList();
        //todo удалить у студента эту тренировку и удалить из филиала эту тренировку
    }
}
