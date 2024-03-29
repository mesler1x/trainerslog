package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

@Mapper(componentModel = "spring", uses = StudentListMapper.class)
public abstract class GroupMapper {
    public abstract GroupDTO entityToResponse(GroupEntity groupEntity);
    public abstract GroupEntity responseToEntity(GroupDTO groupDTO);
}
