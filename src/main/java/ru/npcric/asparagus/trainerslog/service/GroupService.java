package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.service.mapper.GroupMapper;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupService {
    GroupRepository groupRepository;
    GroupMapper groupMapper;

    public GroupFullResponse createGroup(GroupDTO groupDTO) {
        GroupEntity groupEntity = groupMapper.responseToEntity(groupDTO);
        GroupEntity group = groupRepository.save(groupEntity);
        return groupMapper.entityToFullResponse(group);
    }
}
