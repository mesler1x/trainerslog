package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.AddStudentInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentsInGroupResponse;
import ru.npcric.asparagus.trainerslog.service.StudentService;

@Validated
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/trainerslog/api/v1/student")
@Tag(name = "Контроллер ученика", description = "Контроллер для управления учениками федерации айкидо")
public class StudentController {
    StudentService studentService;

    @Operation(
            summary = "Создание студента"
    )
    @RolesAllowed("ADMIN")
    @PostMapping("/create")
    public ResponseEntity<StudentCreateResponse> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.createStudent(studentDTO));
    }


    @Operation(
            summary = "Добавление студента в существующую группу"
    )
    @RolesAllowed({"COACH","ADMIN"})
    @PatchMapping("/addStudentInExistingGroup")
    public ResponseEntity<StudentWithGroupSmallResponse> addStudentInExistingGroup(@RequestBody AddStudentInGroupRequest request) {
        return ResponseEntity.ok().body(studentService.addStudentInGroup(request));
    }

    @Operation(
            summary = "Просмотр всех студентов по имени группы"
    )
    @RolesAllowed({"COACH","ADMIN", "STUDENT"})
    @GetMapping("/getStudentsInGroup")
    public ResponseEntity<StudentsInGroupResponse> getStudentsInGroup(@RequestParam("groupName") String groupNameRequest) {
        return ResponseEntity.ok().body(studentService.getStudentsInGroup(groupNameRequest));
    }

    @Operation(
            summary = "Удаление студента из группы",
            description = "При удалении студента в базе данных у этого пользователя удаляется роль студента"
    )
    @RolesAllowed({"COACH","ADMIN"})
    @DeleteMapping("/deleteStudentFromGroup")
    public ResponseEntity<?> deleteStudentFromGroup(@RequestParam("studentUsername") String studentUsername) {
        studentService.deleteStudentFromGroup(studentUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
