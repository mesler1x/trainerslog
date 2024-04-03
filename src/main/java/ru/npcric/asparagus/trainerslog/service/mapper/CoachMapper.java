package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, GroupMapper.class})
public abstract class CoachMapper {
    public abstract CoachFullResponse entityToFullResponse(CoachEntity coachEntity);
    public abstract CoachEntity smallResponseToEntity(CoachDTO coachDTO);
    public abstract CoachSmallResponse entityToSmallResponse(CoachEntity coachEntity);
}