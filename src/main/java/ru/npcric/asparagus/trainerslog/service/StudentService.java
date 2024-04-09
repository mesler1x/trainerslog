package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.service.factory.StudentFactory;
import ru.npcric.asparagus.trainerslog.service.mapper.StudentMapper;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentService {
    StudentRepository studentRepository;
    StudentFactory studentFactory;
    StudentMapper studentMapper;
    public StudentCreateResponse createStudent(StudentDTO studentDTO) {
        StudentEntity.StudentContext context = studentFactory.createContext(studentDTO);

        StudentEntity student = new StudentEntity(context);
        StudentEntity studentEntityWithId = studentRepository.save(student);

        return studentMapper.entityToResponse(studentEntityWithId);
    }
}
