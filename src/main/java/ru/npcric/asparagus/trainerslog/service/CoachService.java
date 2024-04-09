package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.service.factory.CoachFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachService {
    CoachRepository coachRepository;
    CoachFactory coachFactory;

    //@RolesAllowed("ADMIN") включить при настройке security
    public CoachFullResponse createCoach(CoachDTO coachDTO) {
        CoachEntity.CoachContext coachContext = coachFactory.createContext(coachDTO);
        CoachEntity coachEntity = new CoachEntity(coachContext);
        CoachEntity coachEntityWithId = coachRepository.save(coachEntity);
        return new CoachFullResponse(coachEntityWithId.getId(),
                coachEntityWithId.getName(),
                coachDTO.filialDTO(), coachDTO.username());
    }
}
