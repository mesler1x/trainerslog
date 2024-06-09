package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeConfirmRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeCreateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.cheque.ChequeDeleteRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.ChequeFullResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.ChequeService;

import java.time.LocalDateTime;
import java.util.List;

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
    @PostMapping("/add")
    public ChequeFullResponse addNewCheque(@AuthenticationPrincipal UserEntity user, @RequestBody ChequeCreateRequest chequeCreateRequest) {
        return chequeService.addNewChequeToStudent(user, chequeCreateRequest);
    }

    @Operation(summary = "Получает все чеки студента", description = "Получает чеки студента по его username")
    @GetMapping("/get")
    public List<ChequeFullResponse> getChequesFromStudent(@RequestParam String username) {
        return chequeService.getChequesByUsername(username);
    }

    @Operation(summary = "Получает все чеки студента", description = "Получает все чеки студента по его авторизации")
    @GetMapping("/getByAuth")
    public List<ChequeFullResponse> getChequesByAuthFromStudent(@AuthenticationPrincipal UserEntity user) {
        return chequeService.getChequesByUsername(user.getUsername());
    }

    @Operation(summary = "Подтвреждение чека тренером", description = "Подтверждает чек студента об оплате")
    @PatchMapping("/confirm")
    public ChequeFullResponse confirmCheque(@RequestBody ChequeConfirmRequest request){
        return chequeService.confirmCheque(request);
    }

    @Operation(summary = "Удаляет чек пользователя", description = "Удаляет чек пользователя")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCheque(@AuthenticationPrincipal UserEntity user, @RequestBody ChequeDeleteRequest request) {
        chequeService.deleteChequeFromStudent(user, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
