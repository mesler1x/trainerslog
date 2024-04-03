package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public abstract class GroupListMapper {
    public abstract  List<GroupEntity> responseToEntity(List<GroupDTO> dloList);
    public abstract  List<GroupSmallResponse> entityToSmallResponse(List<GroupEntity> modelList);
    public abstract  List<GroupFullResponse> entityToFullResponse(List<GroupEntity> modelList);
}
