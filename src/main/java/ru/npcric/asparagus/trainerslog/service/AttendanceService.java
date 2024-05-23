package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.TrainingRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.AttendanceDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.attendance.GroupAndDateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceForGroupResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.AttendanceResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.attendance.MonthlyAttendanceResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
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

    public AttendanceResponse markAttendance(AttendanceDTO attendanceDTO) {
        StudentEntity student = studentRepository.findByFullName(attendanceDTO.studentName());
        TrainingEntity training = trainingRepository.findByDate(attendanceDTO.dateTime());
        student.getTrainingEntityList().add(training);

        return new AttendanceResponse(student.getId(), training.getId());
    }

    public AttendanceForGroupResponse findStudentsByGroupAndDate(GroupAndDateRequest groupAndDateRequest) {
        TrainingEntity trainingEntity = trainingRepository.findByDate(groupAndDateRequest.dateTime());
        List<StudentEntity> studentEntityList = trainingEntity.getStudentEntityList();

        return new AttendanceForGroupResponse(
                studentEntityList.stream().map(StudentEntity::getFullName).toList());
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
