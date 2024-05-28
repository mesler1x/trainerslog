package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.coach.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.AlreadyExistException;
import ru.npcric.asparagus.trainerslog.adapter.web.errors.UserNotFoundException;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserRole;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachFactory {
    FilialRepository filialRepository;
    UserRepository userRepository;


    public CoachEntity.CoachContext createContext(CoachDTO coachDTO) {
        FilialDTO filialDTO = coachDTO.filialDTO();
        String username = coachDTO.username();

        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UserNotFoundException(username);
        else if(user.get().getAuthorities().contains(UserRole.ROLE_COACH)){
            throw new AlreadyExistException("Coach");
        }

        UserEntity userEntity = user.get();
        userEntity.getAuthorities().add(UserRole.ROLE_COACH);
        userEntity.getAuthorities().remove(UserRole.ROLE_DEFAULT);
        FilialEntity filialEntity = filialRepository.findByAddress(filialDTO.address());

        CoachEntity.CoachContext context = new CoachEntity.CoachContext(coachDTO.name(), coachDTO.email(),
                coachDTO.phoneNumber(), coachDTO.sex(), coachDTO.birthDate(), filialEntity, userEntity);
        return context;
    }
}
