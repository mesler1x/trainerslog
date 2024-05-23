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
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.util.List;

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
}
