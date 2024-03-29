package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public abstract class GroupListMapper {
    public abstract List<GroupDTO> entityListToResponseList(List<GroupEntity> groupEntityList);
    public abstract List<GroupEntity> responseListToEntityList(List<GroupDTO> groupDTOList);
}
