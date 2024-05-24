package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.StudentWithChequesResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.ChequeService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
@RequiredArgsConstructor
@RequestMapping("/trainerslog/api/v1/cheque")
@Tag(name = "Контроллер чека", description = "Контроллер для управления чеком ученика")
public class ChequeController {
    ChequeService chequeService;

    @Operation(
            summary = "Создание нового чека",
            description = "Добавление новой ссылки на чек студенту"
    )
    @RolesAllowed("STUDENT")
    @PatchMapping("/add")
    public StudentWithChequesResponse addNewCheque(@AuthenticationPrincipal UserEntity user, @RequestParam String link) {
        return chequeService.addNewChequeToStudent(user, link);
    }

    @Operation(summary = "Получает все чеки студента", description = "Получает чеки студента по его username")
    @GetMapping("/get")
    @RolesAllowed("COACH")
    public StudentWithChequesResponse getChequesFromStudent(@RequestParam String username) {
        return chequeService.getChequesByUsername(username);
    }

    @RolesAllowed("STUDENT")
    @Operation(summary = "Удаляет чек пользователя", description = "Удаляет чек пользователя")
    @PatchMapping("/delete")
    public StudentWithChequesResponse deleteCheque(@AuthenticationPrincipal UserEntity user, @RequestParam String link) {
        return chequeService.deleteChequeFromStudent(user, link);
    }
}
