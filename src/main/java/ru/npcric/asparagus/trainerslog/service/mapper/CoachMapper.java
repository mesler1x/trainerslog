package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

@Mapper(componentModel = "spring", uses = GroupListMapper.class)
public abstract class CoachMapper {
    public abstract CoachFullResponse entityToResponse(CoachEntity coachEntity);
    public abstract CoachEntity responseToEntity(CoachDTO coachDTO);
}
