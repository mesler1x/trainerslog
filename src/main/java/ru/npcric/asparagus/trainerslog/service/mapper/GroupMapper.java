package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CoachMapper.class})
public abstract class GroupMapper {
    public abstract GroupDTO entityToSmallResponse(GroupEntity groupEntity);
    public abstract GroupEntity smallResponseToEntity(GroupDTO groupDTO);
    public abstract GroupFullResponse entityToFullResponse(GroupEntity groupEntity);
    public abstract GroupEntity fullResponseToEntity(GroupFullResponse groupFullResponse);
}
