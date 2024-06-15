package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateCommentRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateTimeRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingsForWeekRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingsForWeekResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrainingService {
    GroupRepository groupRepository;
    TrainingRepository trainingRepository;
    StudentRepository studentRepository;
    // todo - тренер начинает тренировку и отмечает учеников по именам

    public TrainingCreateResponse createTraining(TrainingDTO trainingDTO) {
        GroupEntity groupEntity = groupRepository.findByGroupName(trainingDTO.groupName());
        TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setGroup(groupEntity);
        trainingEntity.setDate(trainingDTO.date());
        trainingEntity.setEndDate(trainingDTO.endDate());
        trainingEntity.setComment(trainingDTO.comment());
        TrainingEntity trainingEntityWithId = trainingRepository.save(trainingEntity);
        return new TrainingCreateResponse(trainingEntityWithId.getId(), trainingEntityWithId.getGroup().getGroupName(),
                trainingEntityWithId.getDate(), trainingEntityWithId.getEndDate(), trainingEntityWithId.getComment());
    }

    public TrainingsForWeekResponse getGroupTrainingsForWeek(TrainingsForWeekRequest trainingsForWeekRequest){
        String groupName = trainingsForWeekRequest.groupName();
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        List<TrainingDTO> trainingsForWeek = new ArrayList<>();
        for(TrainingEntity trainingEntity : groupEntity.getTrainingEntities()){
            LocalDateTime trainingDate = trainingEntity.getDate();
            if(trainingDate.isAfter(trainingsForWeekRequest.mondayDate())
                    && trainingDate.isBefore(trainingsForWeekRequest.mondayDate().plusWeeks(1))){
                trainingsForWeek.add(new TrainingDTO(groupName, trainingDate, trainingEntity.getEndDate(), trainingEntity.getComment()));
            }
        }
        return new TrainingsForWeekResponse(groupName, trainingsForWeek);
    }

    public TrainingCreateResponse updateTrainingTime(TrainingUpdateTimeRequest request){
        TrainingEntity trainingEntity = trainingRepository.findByDate(request.originalStartDate());
        if(request.newStartDate() != null) trainingEntity.setDate(request.newStartDate());
        if(request.newEndDate() != null) trainingEntity.setEndDate(request.newEndDate());
        return new TrainingCreateResponse(trainingEntity.getId(), trainingEntity.getGroup().getGroupName(),
                trainingEntity.getDate(), trainingEntity.getEndDate(), trainingEntity.getComment());

    }

    public TrainingCreateResponse updateTrainingComment(TrainingUpdateCommentRequest request){
        TrainingEntity trainingEntity = trainingRepository.findByDate(request.startDate());
        if(request.comment() != null) trainingEntity.setComment(request.comment());
        TrainingEntity newTrainingEntity = trainingRepository.save(trainingEntity);
        return new TrainingCreateResponse(newTrainingEntity.getId(), newTrainingEntity.getGroup().getGroupName(),
                newTrainingEntity.getDate(), newTrainingEntity.getEndDate(), newTrainingEntity
                .getComment());
    }

    public TrainingsForWeekResponse getStudentTrainingsForWeek(UserEntity userEntity, LocalDateTime mondayDate){
        Optional<StudentEntity> studentEnt = studentRepository.findByUser_Username(userEntity.getUsername());
        if(studentEnt.isEmpty()) throw new UserNotFoundException(userEntity.getUsername());
        StudentEntity studentEntity = studentEnt.get();
        return getGroupTrainingsForWeek(new TrainingsForWeekRequest(studentEntity.getGroup().getGroupName(), mondayDate));
    }

    public void deleteTraining(TrainingDTO trainingDTO){
        TrainingEntity trainingEntity = trainingRepository.findByDate(trainingDTO.date());
        trainingRepository.delete(trainingEntity);
        //todo удалить у студента эту тренировку и удалить из филиала эту тренировку
    }
}
