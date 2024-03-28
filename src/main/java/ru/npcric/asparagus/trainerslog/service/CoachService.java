package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.Coach;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.Group;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.Student;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.CoachResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.GroupResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachService {
    CoachRepository coachRepository;

    //Метод выводит всех тренеров, а именно, имя тренеров и для каждого тренера его группы
    public List<CoachResponse> findAll() {
        List<CoachEntity> coachEntities = coachRepository.findAll();
        List<CoachResponse> coachesResponse = new ArrayList<>();
        for(CoachEntity coachEntity : coachEntities) {
            List<GroupEntity> groupEntities = coachEntity.getGroups();
            List<GroupResponse> groupsResponse = new ArrayList<>();
            for(GroupEntity group : groupEntities) {
                GroupResponse groupResponse = new GroupResponse(group.getGroupName(), group.getDates());
                groupsResponse.add(groupResponse);
            }

            CoachResponse coach = new CoachResponse(coachEntity.getName(), groupsResponse);
            coachesResponse.add(coach);
        }
        return coachesResponse;
    }
}
