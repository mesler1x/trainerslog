package ru.npcric.asparagus.trainerslog.adapter.web;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.service.RegistrationService;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/trainerslog/api/v1/public")
@Validated
public class RegistrationController {
    RegistrationService registrationService;
    @PostMapping("/registration")
    public void registration(@RequestBody @Valid RegistrationRequest registrationRequest) {
        registrationService.registerUser(registrationRequest);
    }
}
