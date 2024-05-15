package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.training.TrainingCreateResponse;
import ru.npcric.asparagus.trainerslog.service.TrainingService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/trainerslog/api/v1/training")
@Tag(name = "Контроллер тренировки", description =
        "Контроллер для управления тренировками которые проводят тренеры федерации айкидо")
//@RolesAllowed("DEFAULT")
public class TrainingController {
    TrainingService trainingService;

    @PostMapping("/createTraining")
    public ResponseEntity<TrainingCreateResponse> createTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        return ResponseEntity.ok().body(trainingService.createTraining(trainingDTO));
    }

    @DeleteMapping("/deleteTraining")
    public ResponseEntity<?> deleteTraining(@RequestBody @Valid TrainingDTO trainingDTO) {
        trainingService.deleteTraining(trainingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
