package ru.npcric.asparagus.trainerslog.service.mapper;

import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentCreateResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

@Component
public class StudentMapper {
    public StudentCreateResponse entityToResponse(StudentEntity studentEntity) {
        return new StudentCreateResponse(
                studentEntity.getId(),
                studentEntity.getFullName(),
                studentEntity.getSex(),
                studentEntity.getBirthDate(),
                studentEntity.getPhoneNumber(),
                studentEntity.getParentPhoneNumber(),
                studentEntity.getParentFullName(),
                studentEntity.getEemail(),
                studentEntity.getBalance());

    }
    public StudentWithGroupSmallResponse entityToSmallResponse(StudentEntity studentEntity){
        return new StudentWithGroupSmallResponse(studentEntity.getId(), studentEntity.getFullName(), studentEntity.getGroup().getGroupName());
    }
}
