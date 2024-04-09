package ru.npcric.asparagus.trainerslog.service.factory;

import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
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
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");

        UserEntity userEntity = user.get();
        userEntity.getAuthorities().add(UserRole.COACH);
        userEntity.getAuthorities().remove(UserRole.DEFAULT);
        FilialEntity filialEntity = filialRepository.findByAddress(filialDTO.address());

        CoachEntity.CoachContext context = new CoachEntity.CoachContext(coachDTO.name(), filialEntity, userEntity);
        return context;
    }
}
