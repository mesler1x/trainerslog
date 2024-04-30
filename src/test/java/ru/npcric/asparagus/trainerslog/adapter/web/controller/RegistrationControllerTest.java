package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.service.RegistrationService;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
    @Mock
    RegistrationService registrationService;

    @InjectMocks
    RegistrationController registrationController;

    @Test
    void registerUser_SavesUserOnGivenRequestParams() {
        RegistrationRequest registrationRequest =
                new RegistrationRequest("sample_username","password");
        doReturn(HttpStatus.OK).when(this.registrationService).registerUser(registrationRequest);
        
    }
}
