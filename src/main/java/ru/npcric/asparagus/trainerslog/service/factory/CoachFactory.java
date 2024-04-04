package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.repository.CoachRepository;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.CoachDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
import ru.npcric.asparagus.trainerslog.domain.CoachEntity;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoachFactory {
    FilialRepository filialRepository;


    public CoachEntity.CoachContext createContext(CoachDTO coachDTO) {
        FilialDTO filialDTO = coachDTO.filialDTO();
        FilialEntity filialEntity = filialRepository.findByAddress(filialDTO.address());
        CoachEntity.CoachContext context = new CoachEntity.CoachContext(coachDTO.name(), filialEntity);
        return context;
    }
}
