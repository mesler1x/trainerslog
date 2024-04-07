package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupFactory {
    StudentRepository studentRepository;

    public GroupEntity.GroupContext createContext(GroupDTO groupDTO) {
        List<StudentEntity> students = groupDTO.studentLogins()
                .stream().
                map(studentRepository::findByFullName)
                .toList();
        GroupEntity.GroupContext context = new GroupEntity.GroupContext(
                groupDTO.groupName(), students, groupDTO.dates()
        );

        return context;
    }
}
