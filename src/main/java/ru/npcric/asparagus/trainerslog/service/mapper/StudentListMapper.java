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
public interface StudentListMapper {
    List<StudentEntity> responseToEntity(List<StudentDTO> dloList);
    List<StudentEntity> entityToSmallResponse(List<GroupEntity> modelList);
    List<GroupFullResponse> entityToFullResponse(List<GroupEntity> modelList);
}
