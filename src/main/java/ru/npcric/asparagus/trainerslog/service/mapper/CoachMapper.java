package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

@Mapper(componentModel = "spring", uses = {StudentListMapper.class, GroupListMapper.class})
public interface CoachMapper {
    CoachFullResponse entityToFullResponse(CoachEntity coachEntity);
    CoachEntity smallResponseToEntity(CoachDTO coachDTO);
    CoachSmallResponse entityToSmallResponse(CoachEntity coachEntity);
}