package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, FilialMapper.class})
public abstract class CoachMapper {
    public abstract CoachFullResponse entityToFullResponse(CoachEntity coachEntity);
    public abstract CoachEntity smallResponseToEntity(CoachDTO coachDTO);
    public abstract CoachSmallResponse entityToSmallResponse(CoachEntity coachEntity);
}