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
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.StudentUpdateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.ChequeFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.user.UsernameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.ChequeEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.factory.StudentFactory;
import ru.npcric.asparagus.trainerslog.service.mapper.ChequeMapper;
import ru.npcric.asparagus.trainerslog.service.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class StudentService {
    StudentRepository studentRepository;
    StudentFactory studentFactory;
    StudentMapper studentMapper;
    GroupRepository groupRepository;
    ChequeMapper chequeMapper;

    public StudentCreateResponse createStudent(StudentDTO studentDTO) {
        StudentEntity.StudentContext context = studentFactory.createContext(studentDTO);

        StudentEntity student = new StudentEntity(context);
        StudentEntity studentEntityWithId = studentRepository.save(student);

        return studentMapper.entityToResponse(studentEntityWithId);
    }

    public StudentWithGroupSmallResponse addStudentInGroup(AddStudentInGroupRequest request) {
        String studentUserName = request.studentUserName();
        String groupName = request.groupName();

        GroupEntity group = groupRepository.findByGroupName(groupName);
        Optional<StudentEntity> student = studentRepository.findByUser_Username(studentUserName);

        if (student.isEmpty()) throw new UserNotFoundException(studentUserName);
        StudentEntity studentEntity = student.get();
        studentEntity.setGroup(group);
        group.getStudents().add(studentEntity);


        return studentMapper.entityToSmallResponse(studentEntity);
    }
    public GroupInfoResponse getStudentsInGroup(String groupName) {
        List<StudentsInGroupResponse> studentsWithInfo = new ArrayList<>();
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        List<StudentEntity> studentNames = groupEntity.getStudents();
        for(StudentEntity studentEntity : studentNames) {
            studentsWithInfo.add(new StudentsInGroupResponse(studentEntity.getFullName(), studentEntity.getBirthDate(),
                    studentEntity.getAge(), studentEntity.getParentPhoneNumber()));
        }
        return new GroupInfoResponse(studentsWithInfo);
    }

    public void deleteStudentFromGroup(String studentUsername) {
        Optional<StudentEntity> student =  studentRepository.findByUser_Username(studentUsername);
        if (student.isEmpty()) throw new UserNotFoundException(studentUsername);
        student.get().setGroup(null);
    }


    public StudentCreateResponse getStudentByUsername(String studentUsername) {
        Optional<StudentEntity> student = studentRepository.findByUser_Username(studentUsername);
        if(student.isEmpty()) throw new UserNotFoundException(studentUsername);
        return studentMapper.entityToResponse(student.get());
    }

    public StudentCreateResponse updateStudentInfo(StudentUpdateRequest request, UserEntity userStudent){
        Optional<StudentEntity> student = studentRepository.findByUser_Username(userStudent.getUsername());
        if(student.isEmpty()) throw new UserNotFoundException(userStudent.getUsername());
        StudentEntity studentEntity = student.get();

        studentEntity.setFullName(request.newFullName());
        studentEntity.setBirthDate(request.newBirthDate());
        studentEntity.setParentPhoneNumber(request.newParentPhoneNumber());
        studentEntity.setEemail(request.newEmail());

        StudentEntity studentEntityWithId = studentRepository.save(studentEntity);
        return studentMapper.entityToResponse(studentEntityWithId);
    }

    public List<StudentDebtResponse> getStudentsInGroupWithDebts(String groupName){
        List<StudentDebtResponse> responses = new ArrayList<>();
        GroupEntity group = groupRepository.findByGroupName(groupName);
        List<StudentEntity> students = group.getStudents();
        for(StudentEntity studentEntity : students) {
            int debt = (studentEntity.getBalance() < 0) ? studentEntity.getBalance() * (-1) : 0;
            List<ChequeFullResponse> chequeFullResponses = new ArrayList<>();
            List<ChequeEntity> chequeEntities = studentEntity.getChequeEntities();
            for(ChequeEntity cheque: chequeEntities){
                chequeFullResponses.add(chequeMapper.entityToFullResponse(cheque, debt, studentEntity.getBalance()));
            }
            responses.add(new StudentDebtResponse(studentEntity.getFullName(), debt, chequeFullResponses));
        }
        return responses;
    }

    public UsernameResponse getUsernameOfStudent(String studentFullName) {
        StudentEntity student = studentRepository.findByFullName(studentFullName);
        return new UsernameResponse(student.getUser().getUsername());
    }
}
