package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public abstract class StudentListMapper {
    public abstract List<StudentDTO> entityListToResponseList (List<StudentEntity> studentEntityList);
    public abstract List<StudentEntity> responseListToEntityList (List<StudentDTO> studentDTOList);
}
