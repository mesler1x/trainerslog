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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.AddStudentInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentUpdateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UsernameResponse;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.StudentService;

import java.util.List;

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
    @PostMapping("/create")
    public ResponseEntity<StudentCreateResponse> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return ResponseEntity.ok().body(studentService.createStudent(studentDTO));
    }


    @Operation(
            summary = "Добавление студента в существующую группу"
    )
    @PatchMapping("/addStudentInExistingGroup")
    public ResponseEntity<StudentWithGroupSmallResponse> addStudentInExistingGroup(@RequestBody AddStudentInGroupRequest request) {
        return ResponseEntity.ok().body(studentService.addStudentInGroup(request));
    }

    @Operation(
            summary = "Просмотр всех студентов по имени группы"
    )
    @GetMapping("/getStudentsInGroup")
    public GroupInfoResponse getStudentsInGroup(@RequestParam("groupName") String groupNameRequest) {
        return studentService.getStudentsInGroup(groupNameRequest);
    }

    @Operation(
            summary = "Просмотр задолженностей всех студентов группы",
            description = "Возвращает сумму долга и отправленные чеки для каждого ученика")
    @GetMapping("/getStudentsInGroupWithDebts")
    public List<StudentDebtResponse> getStudentsInGroupWithDebts(@RequestParam String groupName){
        return studentService.getStudentsInGroupWithDebts(groupName);
    }


    @Operation(
            summary = "Удаление студента из группы",
            description = "При удалении студента в базе данных у этого пользователя удаляется роль студента"
    )
    @DeleteMapping("/deleteStudentFromGroup")
    public ResponseEntity<?> deleteStudentFromGroup(@RequestParam("studentUsername") String studentUsername) {
        studentService.deleteStudentFromGroup(studentUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение данных студента по его username"
    )
    @GetMapping("/get")
    public StudentCreateResponse getStudent(@RequestParam("studentUsername") String studentUsername) {
        return studentService.getStudentByUsername(studentUsername);
    }

    @Operation(
            summary = "Получение студента по его авторизации"
    )
    @GetMapping("/getByAuth")
    public StudentCreateResponse getStudentByAuth(@AuthenticationPrincipal UserEntity userStudent) {
        return studentService.getStudentByUsername(userStudent.getUsername());
    }

    @Operation(
            summary = "Обновление полей студента"
    )
    @PatchMapping("/updateStudentInfo")
    public StudentCreateResponse updateStudentInfo(@AuthenticationPrincipal UserEntity userStudent, @RequestBody @Valid StudentUpdateRequest request){
        return studentService.updateStudentInfo(request, userStudent);
    }

    @Operation(
            summary = "Получение username пользователя по имени студента"
    )
    @GetMapping("/getUsernameOfStudent")
    public UsernameResponse getUsernameOfStudent(@RequestParam("studentFullName") String studentFullName) {
        return studentService.getUsernameOfStudent(studentFullName);
    }
}
