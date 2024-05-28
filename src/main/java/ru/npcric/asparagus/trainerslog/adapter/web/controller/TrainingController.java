package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateCommentRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateTimeRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingsForWeekRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingCreateResponse;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingsForWeekResponse;
import ru.npcric.asparagus.trainerslog.service.TrainingService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/training")
@Tag(name = "Контроллер тренировки", description =
        "Контроллер для управления тренировками которые проводят тренеры федерации айкидо")
@RolesAllowed({"ADMIN", "COACH"})
public class TrainingController {
    TrainingService trainingService;

    @Operation(
            summary = "Создание тренировки"
    )
    @PostMapping("/createTraining")
    public TrainingCreateResponse createTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        return trainingService.createTraining(trainingDTO);
    }

    //todo - cascade
    @Operation(
            summary = "Удаление тренировки"
    )
    @DeleteMapping("/deleteTraining")
    public ResponseEntity<?> deleteTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        trainingService.deleteTraining(trainingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RolesAllowed({"ADMIN"})
    @GetMapping("/getGroupTrainingsForWeek")
    public TrainingsForWeekResponse getGroupTrainingsForWeek(@RequestBody TrainingsForWeekRequest trainingsForWeekRequest) {
        return trainingService.getGroupTrainingsForWeek(trainingsForWeekRequest);
    }

    //todo метод по обновлению времени тренировки
    @PatchMapping("/updateTrainingTime")
    public TrainingCreateResponse updateTrainingTime(@RequestBody @Valid TrainingUpdateTimeRequest request) {
        return trainingService.updateTrainingTime(request);
    }

    //todo метрод по обновлению комметария к тренировке
    @PatchMapping("/updateTrainingComment")
    public TrainingCreateResponse updateTrainingComment(@RequestBody @Valid TrainingUpdateCommentRequest request){
        return trainingService.updateTrainingComment(request);
    }

}
