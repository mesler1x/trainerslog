package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.*;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/coach")
@Tag(name = "Контроллер тренера", description = "Контроллер для управления тренерами федерации айкидо")
//@RolesAllowed("DEFAULT")
public class CoachController {
    CoachService coachService;

    //@RolesAllowed("ADMIN")
    @Operation(
            summary = "Создание тренера",
            description = "Создание тренера по приходящему username " +
                    "пример: человек даёт свой username, админ назначает его " +
                    "как тренера, если он к примеру прошёл собеседование"

    )
    @PostMapping("/createCoach")
    public CoachFullResponse createCoach(@RequestBody @Valid CoachDTO coachDTO) {
        return coachService.createCoach(coachDTO);
    }

    @Operation(
            summary = "Получение тренеров в филиале",
            description = "Получение имён всех тренеров в филиале, по указанному адресу"
    )
    @GetMapping("/getCoachesInFilial")
    public AllCoachesInFilialResponse getCoachesInFilial(@RequestBody @Valid FilialDTO filialDTO) {
        return coachService.getCoachesInFilial(filialDTO);
    }


    @Operation(
            summary = "Получение всех тренеров во всех филиалах"
    )
    @GetMapping("/getAllCoaches")
    public List<CoachWithFilialNameResponse> getAllCoaches() {
        return coachService.getAllCoaches();
    }


    @Operation(
            summary = "Получение всех групп текущего тренера",
            description = "Получение групп авторизованного тренера"
    )
    @GetMapping("/getCoachGroups")
    public CoachGroupsResponse getCoachGroups(@AuthenticationPrincipal UserEntity userCoach) {
        return coachService.getCoachGroups(userCoach);
    }

    @Operation(
            summary = "Получение всех студентов привязанного к тренеру",
            description = "Получение всех студентов авторизованного тренера"
    )
    @GetMapping("/getAllCoachStudents")
    public CoachStudentsResponse getAllCoachStudents(@AuthenticationPrincipal UserEntity userCoach) {
        return coachService.getCoachStudents(userCoach);
    }

    @Operation(
            summary = "Удаление тренера",
            description = "Удаление тренера из базы данных, " +
                    "все группы тренера после этого ссылаются на null и " +
                    "все студенты аналогично перестают иметь тренера"
    )
    @DeleteMapping("/deleteCoach")
    public void deleteCoach(@RequestParam String username) {
        coachService.deleteCoach(username);
    }
}
