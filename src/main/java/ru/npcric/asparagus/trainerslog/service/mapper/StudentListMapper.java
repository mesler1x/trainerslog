package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public abstract class StudentListMapper {
    public abstract List<StudentEntity> responseToEntity(List<StudentDTO> dloList);
    public abstract List<StudentEntity> entityToSmallResponse(List<GroupEntity> modelList);
    public abstract List<GroupFullResponse> entityToFullResponse(List<GroupEntity> modelList);
}
