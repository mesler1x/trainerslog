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
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.AddStudentInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentsInGroupResponse;
import ru.npcric.asparagus.trainerslog.service.StudentService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    StudentService studentService;
    @InjectMocks
    StudentController studentController;

//    @Test
//    @DisplayName("POST /trainerslog/api/v1/student/create Проверяет создание студента")
//    void createStudent_ValidatingCreatingOfStudent_ReturnsValidResponseEntity() {
//        StudentDTO studentDTO = new StudentDTO(
//                null,
//                "Mesler Roman Aleksandrovich",
//                "м",
//                LocalDate.now(),
//                2,
//                "+79623881729",
//                "+79623812749",
//                "Mesler Aleksander Aleksandrovich",
//                null,
//                "roman_"
//        );
//        StudentCreateResponse studentCreateResponse = new StudentCreateResponse(
//                1L,
//                studentDTO.fullName(),
//                studentDTO.sex(),
//                studentDTO.birthDate(),
//                studentDTO.q(),
//                studentDTO.phoneNumber(),
//                studentDTO.parentPhoneNumber(),
//                studentDTO.parentFullName()
//        );
//        Mockito.when(studentService.createStudent(studentDTO)).thenReturn(studentCreateResponse);
//
//        ResponseEntity<StudentCreateResponse> responseEntity = studentController.createStudent(studentDTO);
//
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(studentCreateResponse, responseEntity.getBody());
//        Mockito.verify(studentService).createStudent(studentDTO);
//    }

    @Test
    @DisplayName("PUT /trainerslog/api/v1/student/addStudentInExistingGroup Проверяет добавление студента в существующую группу")
    public void addStudentInExistingGroup_ValidatingGroupWithNewStudent_ReturnsValidResponseEntity() {
        AddStudentInGroupRequest addStudentInGroupRequest = new AddStudentInGroupRequest("ivan_", "AT-12");
        StudentWithGroupSmallResponse studentWithGroupSmallResponse = new StudentWithGroupSmallResponse(
                1L, "Ivanov Ivan Ivanovich", "AT-12"
        );
        Mockito.when(studentService.addStudentInGroup(addStudentInGroupRequest)).thenReturn(studentWithGroupSmallResponse);

        ResponseEntity<StudentWithGroupSmallResponse> responseEntity = studentController.addStudentInExistingGroup(addStudentInGroupRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentWithGroupSmallResponse, responseEntity.getBody());
        Mockito.verify(studentService).addStudentInGroup(addStudentInGroupRequest);
    }

    /*
    @Test
    @DisplayName("GET /trainerslog/api/v1/student/getStudentsInGroup Проверяет просмотр всех студентов по имени группы")
    void getStudentsInGroup_ValidatingStudentsInCurrentGroup_ReturnsValidResponseEntity() {
        String groupName = "AT-12";
        StudentsInGroupResponse studentsInGroupResponse = new StudentsInGroupResponse(
                "АТ-12", "Ivanov Ivan Ivanovich", List.of("Alekseev Nikita Ruslanovich", "Gorbachev Maksim Gennadievich")
        );
        Mockito.when(studentService.getStudentsInGroup(groupName)).thenReturn(studentsInGroupResponse);

        ResponseEntity<StudentsInGroupResponse> responseEntity = studentController.getStudentsInGroup(groupName);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentsInGroupResponse, responseEntity.getBody());
        Mockito.verify(studentService).getStudentsInGroup(groupName);
    }
     */

    @Test
    @DisplayName("DELETE /trainerslog/api/v1/student/deleteStudentFromGroup Проверяет удаление студента из группы")
    void deleteStudentFromGroup_ReturnsValidResponseEntity() {
        String studentUsername = "ivan_";
        ResponseEntity<?> responseEntity = studentController.deleteStudentFromGroup(studentUsername);
        Mockito.verify(studentService).deleteStudentFromGroup(studentUsername);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
