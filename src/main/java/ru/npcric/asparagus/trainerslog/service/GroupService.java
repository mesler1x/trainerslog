package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.GroupRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.GroupDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.group.UpdateCoachInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.student.AddStudentInGroupRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupAndCoachNameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupFullResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.GroupEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.factory.GroupFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class GroupService {
    GroupRepository groupRepository;
    GroupFactory groupFactory;
    CoachRepository coachRepository;

    //@RolesAllowed("COACH")
    @Transactional
    public GroupFullResponse createGroup(GroupDTO groupDTO, UserEntity user) {
        GroupEntity.GroupContext context = groupFactory.createContext(groupDTO, user);
        GroupEntity groupEntity = new GroupEntity(context);

        GroupEntity groupEntityWithId = groupRepository.save(groupEntity);

        /*
        for (String studentUsername: groupDTO.studentUsernames()) {
            studentService.addStudentInGroup(new AddStudentInGroupRequest(studentUsername,groupDTO.groupName()));
        }
         */
        List<StudentEntity> studentsInGroup = groupEntity.getStudents();
        for(StudentEntity student : studentsInGroup){
            student.setGroup(groupEntity);
        }

        //todo - убрать в маппер
        List<String> studentNames = new ArrayList<>();
        for(StudentEntity s : studentsInGroup) {
            studentNames.add(s.getFullName());
        }

        return new GroupFullResponse(groupEntityWithId.getId(),
                groupEntityWithId.getGroupName(),
                studentNames);
    }

    public GroupFullResponse getGroupByName(String groupName){
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        return new GroupFullResponse(groupEntity.getId(),
                groupEntity.getGroupName(),
                groupEntity.getStudents().stream().map(StudentEntity::getFullName).toList());
    }

    public void deleteGroup(String groupName){
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        Long groupId = groupEntity.getId();
        groupRepository.updateStudentsSetGroupToNull(groupId);
        groupRepository.deleteByGroupName(groupName);
    }

    //todo - добавить тренера в существующую группу в которой тренера уволили (null coach) --> точно тут реализовывать?
    public GroupAndCoachNameResponse updateCoachOfGroup(UpdateCoachInGroupRequest request) {
        String coachUsername = request.username();
        String groupName = request.groupName();
        CoachEntity coachEntity = coachRepository.findByUser_Username(coachUsername);
        GroupEntity groupEntity = groupRepository.findByGroupName(groupName);
        groupEntity.setCoach(coachEntity);

        List<StudentEntity> studentsInGroup = groupEntity.getStudents();
        List<String> studentNames = new ArrayList<>();
        for(StudentEntity s : studentsInGroup) {
            studentNames.add(s.getFullName());
        }
        return new GroupAndCoachNameResponse(groupEntity.getId(), groupName, coachEntity.getName(), studentNames);
    }


}
