package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachUpdateRequest;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach.*;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group.GroupIdAndNameResponse;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.student.StudentWithGroupSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
import ru.npcric.asparagus.trainerslog.domain.StudentEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;
import ru.npcric.asparagus.trainerslog.service.factory.CoachFactory;
import ru.npcric.asparagus.trainerslog.service.mapper.CoachMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class CoachService {
    CoachRepository coachRepository;
    CoachFactory coachFactory;
    FilialRepository filialRepository;

    public CoachFullResponse createCoach(CoachDTO coachDTO) {
        CoachEntity.CoachContext coachContext = coachFactory.createContext(coachDTO);
        CoachEntity coachEntity = new CoachEntity(coachContext);
        CoachEntity coachEntityWithId = coachRepository.save(coachEntity);
        return new CoachFullResponse(coachEntityWithId.getId(),
                coachEntityWithId.getName(), coachEntityWithId.getEemail(), coachEntityWithId.getPhoneNumber(),
                coachEntityWithId.getSex(), coachEntityWithId.getBirthDate(), coachDTO.filialDTO(), coachDTO.username());
    }

    public AllCoachesInFilialResponse getCoachesInFilial(FilialDTO filialDTO) {
        FilialEntity filialEntity = filialRepository.findByAddress(filialDTO.address());
        List<String> coachNames = filialEntity.getCoachesInFilial().stream()
                .map(CoachEntity::getName).toList();
        return new AllCoachesInFilialResponse(filialDTO.name(), coachNames);
    }

    public List<CoachWithFilialNameResponse> getAllCoaches() {
        return coachRepository.findAll()
                .stream().map(CoachMapper::entityToResponse).toList();
    }

    public CoachGroupsResponse getCoachGroups(UserEntity userCoach) {
        CoachEntity coachEntity = coachRepository.findByUser(userCoach);
        List<GroupIdAndNameResponse> groupIdAndNameResponses = coachEntity.getGroups().stream()
                .map(g -> new GroupIdAndNameResponse(g.getId(),g.getGroupName())).toList();
        return new CoachGroupsResponse(coachEntity.getName(), groupIdAndNameResponses);
    }

    public CoachStudentsResponse getCoachStudents(UserEntity userCoach) {
        CoachEntity coachEntity = coachRepository.findByUser(userCoach);
        List<StudentEntity> students = coachEntity.getGroups().stream()
                .flatMap(group -> group.getStudents().stream())
                .toList();
        List<StudentWithGroupSmallResponse> groupsResponse =
                students.stream().
                        map(s -> new StudentWithGroupSmallResponse(s.getId(), s.getFullName(), s.getGroup().getGroupName()))
                        .toList();
        return new CoachStudentsResponse(coachEntity.getName(),
                groupsResponse
                );
    }

    public CoachFullResponse getCoachByUsername(String coachUsername) {
        CoachEntity coach = coachRepository.findByUser_Username(coachUsername);
        FilialEntity filialEntity = coach.getFilial();
        return new CoachFullResponse(coach.getId(), coach.getName(), coach.getEemail(), coach.getPhoneNumber(),
                coach.getSex(), coach.getBirthDate(), new FilialDTO(filialEntity.getFilialName(), filialEntity.getAddress()),
                coachUsername);
    }

    public CoachFullResponse updateCoachInfo(CoachUpdateRequest request, UserEntity userCoach){
        CoachEntity coachEntity = coachRepository.findByUser_Username(userCoach.getUsername());
        coachEntity.setName(request.newName());
        coachEntity.setBirthDate(request.newBirthDate());
        coachEntity.setPhoneNumber(request.newPhoneNumber());
        coachEntity.setEemail(request.newEmailAddress());
        CoachEntity newCoachEntity = coachRepository.save(coachEntity);
        FilialEntity filialEntity = coachEntity.getFilial();

        return new CoachFullResponse(newCoachEntity.getId(), newCoachEntity.getName(), newCoachEntity.getEemail(),
                newCoachEntity.getPhoneNumber(), newCoachEntity.getSex(), newCoachEntity.getBirthDate(),
                new FilialDTO(filialEntity.getFilialName(), filialEntity.getAddress()), userCoach.getUsername());
    }


    public void deleteCoach(String username) {
        CoachEntity coachEntity = coachRepository.findByUser_Username(username);
        UserEntity user = coachEntity.getUser();
        Long coachId = coachEntity.getId();
        coachRepository.updateGroupsSetTrainerToNull(coachId);

        coachRepository.deleteById(coachId);
        user.getAuthorities().remove(UserRole.ROLE_COACH);
        user.getAuthorities().add(UserRole.ROLE_DEFAULT);
    }
}
