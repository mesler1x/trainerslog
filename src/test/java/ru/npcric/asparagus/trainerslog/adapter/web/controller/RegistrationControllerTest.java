package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.service.CoachService;
import ru.npcric.asparagus.trainerslog.service.RegistrationService;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
    @Mock
    RegistrationService registrationService;

    @InjectMocks
    RegistrationController registrationController;

    @Test
    void checkBasicRegistration() {
        RegistrationRequest registrationRequest = new RegistrationRequest("sample_username","password");
    }
}
