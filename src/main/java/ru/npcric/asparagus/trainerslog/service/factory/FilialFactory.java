package ru.npcric.asparagus.trainerslog.service.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
import ru.npcric.asparagus.trainerslog.domain.user.UserEntity;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FilialFactory {

    public FilialEntity.FilialContext createContext(FilialDTO filialDTO) {
        FilialEntity.FilialContext filialContext = new FilialEntity.FilialContext(filialDTO.name(), filialDTO.address());
        return filialContext;
    }
}
