package ru.npcric.asparagus.trainerslog.service.factory;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachFactory {

    GroupRepository groupRepository;

    public CoachEntity.CoachContext createContext(CoachDTO coachDTO) {
        List<GroupEntity> groups = coachDTO.groupDTOS()
                .stream()
                .map(d -> new GroupEntity(
                                d.groupName(),
                                null,
                                 null)
                        )
                .toList();

        return new CoachEntity.CoachContext(
                coachDTO.name(), null , groups
        );
    }
}
