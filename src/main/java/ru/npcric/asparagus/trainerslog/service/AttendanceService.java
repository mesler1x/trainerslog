package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.AttendanceDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.GroupAndDateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.training.TrainingDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceForGroupResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.MonthlyAttendanceResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class AttendanceService {
    StudentRepository studentRepository;
    TrainingRepository trainingRepository;
    GroupRepository groupRepository;

    public AttendanceResponse markAttendance(AttendanceDTO attendanceDTO) {
        StudentEntity student = studentRepository.findByFullName(attendanceDTO.studentName());
        TrainingEntity training = trainingRepository.findByDate(attendanceDTO.dateTime());
        student.getTrainingEntityList().add(training);

        return new AttendanceResponse(student.getId(), training.getId());
    }

    public List<AttendanceForGroupResponse> findStudentsByGroupAndDate(GroupAndDateRequest groupAndDateRequest) {
        List<AttendanceForGroupResponse> attendanceForGroupResponses = new ArrayList<>();
        GroupEntity groupEntity = groupRepository.findByGroupName(groupAndDateRequest.groupName());
        List<StudentEntity> students = groupEntity.getStudents();
        List<TrainingEntity> trainingEntities = new ArrayList<>();
        for(TrainingEntity trainingEntity : groupEntity.getTrainingEntities()){
            LocalDateTime trainingDate = trainingEntity.getDate();
            if(trainingDate.isAfter(groupAndDateRequest.mondayDateTime())
                    && trainingDate.isBefore(groupAndDateRequest.mondayDateTime().plusWeeks(1))){
                trainingEntities.add(trainingEntity);
            }
        }
        for(TrainingEntity trainingEntity : trainingEntities){
            List<String> studentNames = new ArrayList<>();
            for(StudentEntity student : students){
                if(student.getTrainingEntityList().contains(trainingEntity)){
                    studentNames.add(student.getFullName());
                }
            }
            attendanceForGroupResponses.add(new AttendanceForGroupResponse(trainingEntity.getDate(), studentNames));
        }
        return attendanceForGroupResponses;
    }

    public MonthlyAttendanceResponse getMonthlyAttendanceByUsername(String username) {
        List<LocalDateTime> monthlyAttendanceList = new ArrayList<>();
        Optional<StudentEntity> student = studentRepository.findByUser_Username(username);
        if (student.isEmpty()) throw new UserNotFoundException(username);
        StudentEntity studentEntity = student.get();

        List<TrainingEntity> trainingEntities = studentEntity.getTrainingEntityList();
        List<LocalDateTime> actualAttendance = new ArrayList<>();
        for(TrainingEntity training : trainingEntities) {
            actualAttendance.add(training.getDate());
        }
        LocalDateTime monthlySplit = LocalDateTime.now().minusMonths(1);

        for(LocalDateTime dateTime : actualAttendance) {
            if (dateTime.isAfter(monthlySplit)) {
                monthlyAttendanceList.add(dateTime);
            }
        }

        return new MonthlyAttendanceResponse(monthlyAttendanceList);
    }
}
