package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.common.RegistrationRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.auth.RegistrationResponse;
import ru.npcric.asparagus.trainerslog.service.RegistrationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
    @Mock
    RegistrationService registrationService;

    PasswordEncoder passwordEncoder;

    @InjectMocks
    RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    @DisplayName("POST /trainerslog/api/v1/public/registration регистрирует пользователя и шифрует пароль")
    void registration_PasswordEncrypted_ReturnsValidOkResponse() {
        //given
        String password = passwordEncoder.encode("hard_password");
        var registerResponse = new RegistrationResponse("sample_user", password, List.of("DEFAULT"));
        var registerRequest = new RegistrationRequest("sample_user","hard_password");
        Mockito.doReturn(registerResponse).when(this.registrationService).registerUser(registerRequest);

        //when
        var responseEntity = this.registrationController.registration(registerRequest);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(registerResponse, responseEntity.getBody());
        assertEquals(password, responseEntity.getBody().encryptedPassword());
    }
}
