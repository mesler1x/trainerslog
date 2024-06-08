package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UserSmallResponse;
import ru.npcric.asparagus.trainerslog.service.common.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;

    @Test
    @DisplayName("GET /trainerslog/api/v1/user/getAllUsers Проверяет получение всех пользователей системы")
    void getAllUsers_ValidatingAllUsersList_ReturnsValidResponseEntity() {
        List<UserSmallResponse> userSmallResponses = List.of(
            new UserSmallResponse(1L, "ivan_"),
            new UserSmallResponse(2L, "roman_mesler2004")
        );
        Mockito.when(userService.getAllUsers()).thenReturn(userSmallResponses);

        ResponseEntity<List<UserSmallResponse>> responseEntity = userController.getAllUsers();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userSmallResponses, responseEntity.getBody());
        assertThat(userSmallResponses).hasSameSizeAs(responseEntity.getBody()).containsExactlyInAnyOrderElementsOf(responseEntity.getBody());
        Mockito.verify(userService).getAllUsers();
    }
}
