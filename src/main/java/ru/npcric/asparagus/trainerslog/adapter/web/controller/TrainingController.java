package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateCommentRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingUpdateTimeRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingsForWeekRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingsForWeekResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.TrainingService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/training")
@Tag(name = "Контроллер тренировки", description =
        "Контроллер для управления тренировками которые проводят тренеры федерации айкидо")
public class TrainingController {
    TrainingService trainingService;

    @Operation(
            summary = "Создание тренировки"
    )
    @PostMapping("/createTraining")
    public TrainingCreateResponse createTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        return trainingService.createTraining(trainingDTO);
    }

    @Operation(
            summary = "Удаление тренировки"
    )
    @DeleteMapping("/deleteTraining")
    public ResponseEntity<?> deleteTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        trainingService.deleteTraining(trainingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение тренировок студента на неделю"
    )
    @GetMapping("/getStudentTrainingsForWeek")
    public TrainingsForWeekResponse getStudentTrainingsForWeek(@AuthenticationPrincipal UserEntity userEntity,
                                                               @RequestParam("mondayDate")
                                                               @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime mondayDate) {
        return trainingService.getStudentTrainingsForWeek(userEntity, mondayDate);
    }
    @Operation(
            summary = "Получение тренировок группы на неделю"
    )
    @GetMapping("/getGroupTrainingsForWeek")
    public TrainingsForWeekResponse getGroupTrainingsForWeek(@RequestBody TrainingsForWeekRequest trainingsForWeekRequest) {
        return trainingService.getGroupTrainingsForWeek(trainingsForWeekRequest);
    }


    @Operation(
            summary = "Обновление времени тренировки"
    )
    @PatchMapping("/updateTrainingTime")
    public TrainingCreateResponse updateTrainingTime(@RequestBody @Valid TrainingUpdateTimeRequest request) {
        return trainingService.updateTrainingTime(request);
    }


    @Operation(
            summary = "Обновление комментария тренировки"
    )
    @PatchMapping("/updateTrainingComment")
    public TrainingCreateResponse updateTrainingComment(@RequestBody @Valid TrainingUpdateCommentRequest request){
        return trainingService.updateTrainingComment(request);
    }

}
