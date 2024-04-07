package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.service.factory.GroupFactory;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupService {
    GroupRepository groupRepository;
    GroupFactory groupFactory;

    public GroupFullResponse createGroup(GroupDTO groupDTO) {
        GroupEntity.GroupContext context = groupFactory.createContext(groupDTO);
        GroupEntity groupEntity = new GroupEntity(context);
        GroupEntity groupEntityWithId = groupRepository.save(groupEntity);

        return new GroupFullResponse(groupEntityWithId.getId(),
                groupEntityWithId.getGroupName(),
                groupEntity.getStudents().stream().map(StudentEntity::getFullName).toList(),
                groupEntity.getDates());
    }
}
