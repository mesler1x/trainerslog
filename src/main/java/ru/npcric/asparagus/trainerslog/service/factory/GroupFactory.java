package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.StudentRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.GroupDTO;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupFactory {
    StudentRepository studentRepository;
    CoachRepository coachRepository;
    public GroupEntity.GroupContext createContext(GroupDTO groupDTO, UserEntity user) {
        List<String> studentUsernames = groupDTO.studentUsernames();
        List<StudentEntity> studentEntities = studentRepository.findAllByUser_UsernameIn(studentUsernames);

        CoachEntity coachEntity = coachRepository.findByUser(user);

        return new GroupEntity.GroupContext(
                groupDTO.groupName(), studentEntities, groupDTO.dates(), coachEntity
        );
    }
}
