package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CoachMapper.class})
public abstract class GroupMapper {
    public abstract GroupSmallResponse entityToSmallResponse(GroupEntity groupEntity);
    public abstract GroupEntity responseToEntity(GroupDTO groupDTO);
    public abstract GroupFullResponse entityToFullResponse(GroupEntity groupEntity);
    public abstract GroupEntity responseToEntity(GroupFullResponse groupFullResponse);
}
