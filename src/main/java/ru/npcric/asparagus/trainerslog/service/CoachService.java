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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachService {
    CoachRepository coachRepository;

    //Метод выводит всех тренеров, а именно, имя тренеров и для каждого тренера его группы
    public List<CoachSmallResponse> findAll() {
        List<CoachEntity> coachEntities = coachRepository.findAll();
        List<CoachSmallResponse> coachesResponse = new ArrayList<>();
        for(CoachEntity coachEntity : coachEntities) {
            List<GroupEntity> groupEntities = coachEntity.getGroups();
            List<GroupsResponse> groupsResponse = new ArrayList<>();
            for(GroupEntity group : groupEntities) {
                GroupsResponse allGroupsResponseWithCoach =
                        new GroupsResponse(group.getGroupName(), group.getDates());
                groupsResponse.add(allGroupsResponseWithCoach);
            }

            CoachSmallResponse coach = new CoachSmallResponse(coachEntity.getName(), groupsResponse);
            coachesResponse.add(coach);
        }
        return coachesResponse;
    }

    public CoachFullResponse createCoach(CoachDTO coachDTO) {
        CoachEntity coachEntity = new CoachEntity();
        CoachFullResponse coachFullResponse = new CoachFullResponse(null,null,null);
        //todo

        return coachFullResponse;
    }
}
