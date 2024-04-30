package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.UpdateCoachInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupAndCoachNameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.GroupService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {
    private static final Logger log = LoggerFactory.getLogger(GroupControllerTest.class);
    @Mock
    GroupService groupService;
    @InjectMocks
    GroupController groupController;

    @Test
    @DisplayName("POST /trainerslog/api/v1/group/createGroup Проверяет создание группы")
    void createGroup_ValidatingGroupAndCoach_ReturnsValidResponseEntity() {
        GroupDTO groupDTO = new GroupDTO("AT-12",
                List.of("roman_","nikita2004","brawl_stars_the_coolest_game_ever"));
        UserEntity coach = new UserEntity();
        coach.setUsername("ivan_");
        GroupFullResponse groupFullResponse = new GroupFullResponse(1L, groupDTO.groupName(),groupDTO.studentUsernames());
        Mockito.when(groupService.createGroup(groupDTO, coach)).thenReturn(groupFullResponse);

        ResponseEntity<GroupFullResponse> responseEntity = groupController.createGroup(groupDTO, coach);

        assertEquals(groupFullResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groupFullResponse, responseEntity.getBody());
        assertThat(groupFullResponse.studentNames()).hasSameSizeAs(responseEntity.getBody().studentNames())
                .containsExactlyInAnyOrderElementsOf(responseEntity.getBody().studentNames());
        Mockito.verify(groupService).createGroup(groupDTO, coach);
    }

    @Test
    @DisplayName("DELETE /trainerslog/api/v1/group/deleteGroup Проверяет удаление группы")
    void deleteGroup_ReturnsValidResponseEntity() {
        String groupName = "AT-12";
        ResponseEntity<?> responseEntity = groupController.deleteGroup(groupName);

        Mockito.verify(groupService).deleteGroup(groupName);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("PUT /trainerslog/api/v1/group/updateGroup Проверяет обновление группы тренера")
    public void updateCoachOfGroup_ReturnsValidResponseEntity() {
        UpdateCoachInGroupRequest updateCoachInGroupRequest = new UpdateCoachInGroupRequest("ivan_", "AT-12");
        List<String> studentNames = List.of("Alekseev Nikita Ruslanovich", "Gorbachev Maksim Gennadievich");
        GroupAndCoachNameResponse groupAndCoachNameResponse = new GroupAndCoachNameResponse
                (1L, "AT-12","Mesler Roman Aleksandrovich", studentNames);
        Mockito.when(groupService.updateCoachOfGroup(updateCoachInGroupRequest)).thenReturn(groupAndCoachNameResponse);

        ResponseEntity<GroupAndCoachNameResponse> responseEntity = groupController.updateCoachOfGroup(updateCoachInGroupRequest);

        assertEquals(groupAndCoachNameResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groupAndCoachNameResponse, responseEntity.getBody());
        Mockito.verify(groupService).updateCoachOfGroup(updateCoachInGroupRequest);
    }
}
