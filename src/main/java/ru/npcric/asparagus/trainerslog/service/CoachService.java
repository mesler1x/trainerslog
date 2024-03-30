package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachFullResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachSmallResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.GroupsResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.service.mapper.CoachMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachService {
    CoachRepository coachRepository;
    CoachMapper coachMapper;

    //Метод выводит всех тренеров, а именно, имя тренеров и для каждого тренера его группы
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
