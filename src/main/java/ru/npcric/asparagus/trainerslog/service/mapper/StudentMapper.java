package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TrainingMapper.class, GroupMapper.class, TicketMapper.class})
public abstract class StudentMapper {
    public abstract StudentDTO entityToResponse(StudentEntity studentEntity);
    public abstract StudentEntity responseToEntity(StudentDTO studentDTO);
    public abstract List<StudentDTO> entityListToREsponseList(List<StudentEntity> studentEntityList);
    public abstract List<StudentEntity> reponseListToEntityList(List<StudentDTO> studentDTOList);
}
