package ru.npcric.asparagus.trainerslog.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.filial.FilialSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;
import ru.npcric.asparagus.trainerslog.service.factory.FilialFactory;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FilialService {
    FilialRepository filialRepository;
    FilialFactory filialFactory;

    public FilialSmallResponse create(FilialDTO filialDTO) {
        FilialEntity.FilialContext context = filialFactory.createContext(filialDTO);
        FilialEntity filialEntity = new FilialEntity(context);
        FilialEntity filialEntityWithId = filialRepository.save(filialEntity);
        FilialSmallResponse filialFullResponse = new FilialSmallResponse(
                filialEntityWithId.getId(), filialEntity.getFilialName(), filialEntity.getAddress()
        );
        return filialFullResponse;
    }
}
