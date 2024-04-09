package ru.npcric.asparagus.trainerslog.service.mapper;

import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

@Component
public class StudentMapper {
    public StudentCreateResponse entityToResponse(StudentEntity studentEntity) {
        return new StudentCreateResponse(
                studentEntity.getId(),
                studentEntity.getFullName(),
                studentEntity.getSex(),
                studentEntity.getBirthDate(),
                studentEntity.getQ(),
                studentEntity.getPhoneNumber(),
                studentEntity.getParentPhoneNumber(),
                studentEntity.getParentFullName());
    }
}
