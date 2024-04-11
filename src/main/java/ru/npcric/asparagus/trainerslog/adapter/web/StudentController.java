package ru.npcric.asparagus.trainerslog.adapter.web;

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
//@RolesAllowed("DEFAULT")
public class StudentController {
    StudentService studentService;
    @PostMapping("/create")
    public StudentCreateResponse createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }


    @PutMapping("/addStudentInExistingGroup")
    public StudentWithGroupSmallResponse addStudentInExistingGroup(@RequestBody AddStudentInGroupRequest request){
        return studentService.addStudentInGroup(request);
    }


    @GetMapping("/getStudentsInGroup")
    public StudentsInGroupResponse getStudentsInGroup(@RequestParam("groupName") String groupNameRequest){
        return studentService.getStudentsInGroup(groupNameRequest);
    }

    @DeleteMapping("/deleteStudentFromGroup")
    public void deleteStudentFromGroup(@RequestParam("studentUsername") String studentUsername) {
        studentService.deleteStudentFromGroup(studentUsername);
    }

    //todo deleteStudent
}
