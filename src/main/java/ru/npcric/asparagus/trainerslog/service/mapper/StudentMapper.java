package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

@Mapper(componentModel = "spring", uses = TicketMapper.class)
public abstract class StudentMapper {
    public abstract StudentDTO entityToResponse(StudentEntity studentEntity);
    public abstract StudentEntity responseToEntity(StudentDTO studentDTO);
}
