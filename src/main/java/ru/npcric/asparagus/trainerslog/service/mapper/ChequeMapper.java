package ru.npcric.asparagus.trainerslog.service.mapper;

import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.StudentWithChequesResponse;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

@Component
public class ChequeMapper {
    public StudentWithChequesResponse entityToResponse(StudentEntity studentEntity) {
        return new StudentWithChequesResponse(studentEntity.getUser().getUsername(),
                studentEntity.getFullName(),studentEntity.getLinkToCheques());
    }
}
