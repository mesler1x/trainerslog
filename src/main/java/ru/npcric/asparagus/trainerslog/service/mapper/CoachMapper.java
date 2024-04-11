package ru.npcric.asparagus.trainerslog.service.mapper;

import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachWithFilialNameResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;

@Component
public class CoachMapper {
    public static CoachWithFilialNameResponse entityToResponse(CoachEntity coachEntity) {

        return new CoachWithFilialNameResponse(coachEntity.getId(),
                coachEntity.getName(),
                coachEntity.getFilial().getFilialName(),
                coachEntity.getUser().getUsername());
    }
}
