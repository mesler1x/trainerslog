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
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupIdAndNameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.CoachService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CoachControllerTest {
    @Mock
    CoachService coachService;
    @InjectMocks
    CoachController coachController;

    @Test
    @DisplayName("POST /trainerslog/api/v1/coach/createCoach Проверяет создание тренера по username")
    void createCoach_ReturnsValidCreatedResponse() {
        UserEntity user = new UserEntity();
        user.setUsername("sample_user");

        FilialDTO filialDTO = new FilialDTO("Юго Западный филиал", "Фурманова 125");
        CoachDTO coachDTO = new CoachDTO("Ivanov Ivan Ivanovich", filialDTO, "sample_user");

        CoachFullResponse coachFullResponse = new CoachFullResponse(1L, "Ivanov Ivan Ivanovich", filialDTO, user.getUsername());
        //задаем поведение
        Mockito.when(coachService.createCoach(coachDTO)).thenReturn(coachFullResponse);

        ResponseEntity<CoachFullResponse> actualResponseEntity = coachController.createCoach(coachDTO);

        assertEquals(coachFullResponse, actualResponseEntity.getBody());
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
        Mockito.verify(coachService).createCoach(coachDTO);
    }

    @Test
    @DisplayName("GET /trainerslog/api/v1/coach/getCoachesInFilial Проверяет вывод тренеров в филиале")
    void getCoachesInFilial_ValidatingAllFilialCoaches_ReturnsValidResponseEntity() {
        FilialDTO filialDTO = new FilialDTO("Юго Западный филиал", "Фурманова 125");
        AllCoachesInFilialResponse allCoachesInFilialResponse = new AllCoachesInFilialResponse(
                "Юго Западный филиал", List.of("Ivanov Ivan Ivanovich", "Romanov Roman Romanovich")
        );
        Mockito.when(coachService.getCoachesInFilial(filialDTO)).thenReturn(allCoachesInFilialResponse);

        ResponseEntity<AllCoachesInFilialResponse> responseEntity = coachController.getCoachesInFilial(filialDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allCoachesInFilialResponse, responseEntity.getBody());
        assertThat(allCoachesInFilialResponse.coachesNames()).hasSameSizeAs(responseEntity.getBody().coachesNames())
                .containsExactlyInAnyOrderElementsOf(responseEntity.getBody().coachesNames());
        Mockito.verify(coachService).getCoachesInFilial(filialDTO);
    }

    @Test
    @DisplayName("GET /trainerslog/api/v1/coach/getAllCoaches Проверяет вывод всех тренеров федерации")
    void getAllCoaches_ValidatingAllFederationCoaches_ReturnsValidResponseEntity() {
        List<CoachWithFilialNameResponse> coachWithFilialNameResponses =
                List.of(new CoachWithFilialNameResponse(1L, "Ivanov Ivan Ivanovich", "Юго Западный филиал", "ivanov_"),
                        new CoachWithFilialNameResponse(1L, "Romanov Roman Romanovich", "Академический филиал", "ivanov_"));
        Mockito.when(coachService.getAllCoaches()).thenReturn(coachWithFilialNameResponses);

        ResponseEntity<List<CoachWithFilialNameResponse>> responseEntity = coachController.getAllCoaches();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(coachWithFilialNameResponses, responseEntity.getBody());
        assertThat(coachWithFilialNameResponses).hasSameSizeAs(responseEntity.getBody()).containsExactlyInAnyOrderElementsOf(responseEntity.getBody());
        Mockito.verify(coachService).getAllCoaches();
    }

    @Test
    @DisplayName("GET /trainerslog/api/v1/coach/getAllCoachStudents Проверяет всех студентов тренера")
    void getCoachGroups_VerifiesCurrentCoachStudents_ReturnsValidResponseEntity() {
        UserEntity user = new UserEntity();
        user.setUsername("ivan_");
        List<StudentWithGroupSmallResponse> students =
                List.of(new StudentWithGroupSmallResponse(1L, "Alekseev Nikita Ruslanovich", "AT-12")
                        , new StudentWithGroupSmallResponse(2L, "Mesler Roman Aleksandrovich", "AT-13"));
        CoachStudentsResponse coachStudentsResponse = new CoachStudentsResponse("Ivanov Ivan Ivanovich", students);
        Mockito.when(coachService.getCoachStudents(user)).thenReturn(coachStudentsResponse);

        ResponseEntity<CoachStudentsResponse> responseEntity = coachController.getAllCoachStudents(user);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(coachStudentsResponse, responseEntity.getBody());
        assertThat(coachStudentsResponse.student()).hasSameSizeAs(responseEntity.getBody().student())
                .containsExactlyInAnyOrderElementsOf(responseEntity.getBody().student());

        Mockito.verify(coachService).getCoachStudents(user);
    }

    @Test
    @DisplayName("GET /trainerslog/api/v1/coach/getCoachGroups Проверяет получение всех групп тренера")
    void getAllCoachStudents_VerifiesAllCoachGroups_ReturnsValidResponseEntity() {
        UserEntity user = new UserEntity();
        user.setUsername("ivan_");
        List<GroupIdAndNameResponse> groups =
                List.of(new GroupIdAndNameResponse(1L, "AT-12"),
                        new GroupIdAndNameResponse(2L, "AT-13"),
                        new GroupIdAndNameResponse(3L, "AT-14"));
        CoachGroupsResponse coachGroupsResponse = new CoachGroupsResponse(
                "Ivanov Ivan Ivanovich", groups
        );
        Mockito.when(coachService.getCoachGroups(user)).thenReturn(coachGroupsResponse);

        ResponseEntity<CoachGroupsResponse> responseEntity = coachController.getCoachGroups(user);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(coachGroupsResponse, responseEntity.getBody());
        assertThat(coachGroupsResponse.groupResponse()).hasSameSizeAs(responseEntity.getBody().groupResponse())
                .containsExactlyInAnyOrderElementsOf(responseEntity.getBody().groupResponse());
        Mockito.verify(coachService).getCoachGroups(user);
    }

    @Test
    @DisplayName("DELETE /trainerslog/api/v1/coach/deleteCoach Проверяет удаление тренера")
    void deleteCoach_VerifiesThatStudentsCoachIsNull_ReturnsValidResponseEntity () {
        String username = "ivan_";

        ResponseEntity<?> responseEntity = coachController.deleteCoach(username);

        Mockito.verify(coachService).deleteCoach(username);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
