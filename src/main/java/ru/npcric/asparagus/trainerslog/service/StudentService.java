package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.AddStudentInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentsInGroupResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.service.factory.StudentFactory;
import ru.npcric.asparagus.trainerslog.service.mapper.StudentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class StudentService {
    StudentRepository studentRepository;
    StudentFactory studentFactory;
    StudentMapper studentMapper;
    GroupRepository groupRepository;

    public StudentCreateResponse createStudent(StudentDTO studentDTO) {
        StudentEntity.StudentContext context = studentFactory.createContext(studentDTO);

        StudentEntity student = new StudentEntity(context);
        StudentEntity studentEntityWithId = studentRepository.save(student);

        return studentMapper.entityToResponse(studentEntityWithId);
    }

    public StudentWithGroupSmallResponse addStudentInGroup(AddStudentInGroupRequest request) {
        String studentUserName = request.studentUserName();
        String groupName = request.groupName();

        //todo - исправить потом что бы группу искало не только по имени
        GroupEntity group = groupRepository.findByGroupName(groupName);
        StudentEntity studentEntity = studentRepository.findByUser_Username(studentUserName);

        studentEntity.setGroup(group);
        group.getStudents().add(studentEntity);

        return studentMapper.entityToSmallResponse(studentEntity);
    }
    public StudentsInGroupResponse getStudentsInGroup(String groupName) {
        //todo - исправить потом что бы группу искало не только по имени
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        List<String> studentNames = groupEntity.getStudents().stream().map(StudentEntity::getFullName).toList();
        String groupCoach = groupEntity.getCoach().getName();
        return new StudentsInGroupResponse(groupName, groupCoach, studentNames);
    }

    public void deleteStudentFromGroup(String studentUsername) {
        //todo - исправить потом что бы группу искало не только по имени
        studentRepository.findByUser_Username(studentUsername).setGroup(null);
    }
}
