package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingCreateResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrainingService {
    GroupRepository groupRepository;
    TrainingRepository trainingRepository;
    // todo - тренер начинает тренировку и отмечает учеников по именам

    public TrainingCreateResponse createTraining(TrainingDTO trainingDTO) {
        GroupEntity groupEntity = groupRepository.findByGroupName(trainingDTO.groupName());
        TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setGroup(groupEntity);
        trainingEntity.setDate(trainingDTO.date());
        TrainingEntity trainingEntityWithId = trainingRepository.save(trainingEntity);
        return new TrainingCreateResponse(trainingEntityWithId.getId(), trainingEntityWithId.getGroup().getGroupName(),
                trainingEntityWithId.getDate());
    }

    public void deleteTraining(TrainingDTO trainingDTO){
        TrainingEntity trainingEntity = trainingRepository.findByDate(trainingDTO.date());
        trainingRepository.delete(trainingEntity);
        //todo удалить у студента эту тренировку и удалить из филиала эту тренировку
    }
}
