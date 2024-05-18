package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.RegistrationResponse;
import ru.npcric.asparagus.trainerslog.service.RegistrationService;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/trainerslog/api/v1/public")
@Validated
@Tag(name = "Контроллер регистрации пользователей")
public class RegistrationController {
    RegistrationService registrationService;

    @Operation(
            summary = "Регистрация пользователей"
    )
    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody @Valid RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse = registrationService.registerUser(registrationRequest);
        return ResponseEntity.ok().body(registrationResponse);
    }
}
