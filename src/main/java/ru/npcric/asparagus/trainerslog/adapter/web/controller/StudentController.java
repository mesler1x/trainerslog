package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
//@RolesAllowed("DEFAULT")
public class StudentController {
    StudentService studentService;
    @Operation(
            summary = "Создание студента"
    )
    @PostMapping("/create")
    public StudentCreateResponse createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }


    @Operation(
            summary = "Добавление студента в существующую группу"
    )
    @PutMapping("/addStudentInExistingGroup")
    public StudentWithGroupSmallResponse addStudentInExistingGroup(@RequestBody AddStudentInGroupRequest request){
        return studentService.addStudentInGroup(request);
    }

    @Operation(
            summary = "Просмотр всех студентов по имени группы"
    )
    @GetMapping("/getStudentsInGroup")
    public StudentsInGroupResponse getStudentsInGroup(@RequestParam("groupName") String groupNameRequest){
        return studentService.getStudentsInGroup(groupNameRequest);
    }

    @Operation(
            summary = "Удаление студента из группы",
            description = "При удалении студента в базе данных у этого пользователя удаляется роль студента"
    )
    @DeleteMapping("/deleteStudentFromGroup")
    public void deleteStudentFromGroup(@RequestParam("studentUsername") String studentUsername) {
        studentService.deleteStudentFromGroup(studentUsername);
    }

    //todo deleteStudent
}
