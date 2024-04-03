package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.service.mapper.CoachMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class CoachService {
    CoachRepository coachRepository;
    CoachMapper coachMapper;


    public List<CoachSmallResponse> findAll() {
        List<CoachEntity> coachEntities = coachRepository.findAll();
        return coachEntities.stream().
        map(coachMapper::entityToSmallResponse).toList();
    }

    public CoachFullResponse createCoach(CoachDTO coachDTO) {
        CoachEntity coachEntity = coachMapper.smallResponseToEntity(coachDTO);
        coachRepository.save(coachEntity);
        return coachMapper.entityToFullResponse(coachEntity);
    }
}
