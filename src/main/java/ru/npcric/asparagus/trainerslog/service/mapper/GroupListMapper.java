package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface GroupListMapper {
    List<GroupEntity> responseToEntity(List<GroupDTO> dloList);
    List<GroupSmallResponse> entityToSmallResponse(List<GroupEntity> modelList);
    List<GroupFullResponse> entityToFullResponse(List<GroupEntity> modelList);
}
