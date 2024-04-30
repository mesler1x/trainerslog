package ru.npcric.asparagus.trainerslog.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.npcric.asparagus.trainerslog.adapter.repository.FilialRepository;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.filial.FilialSmallResponse;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;
import ru.npcric.asparagus.trainerslog.service.factory.FilialFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
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

    public List<FilialSmallResponse> getAll() {
        List<FilialEntity> filialEntityList = filialRepository.findAll();
        return filialEntityList.stream()
                .map(f -> new FilialSmallResponse(f.getId(),f.getFilialName(),f.getAddress())).toList();
    }

    public void deleteFilialByAddress(String address) {
        FilialEntity filialEntity = filialRepository.findByAddress(address);
        Long id = filialEntity.getId();
        filialRepository.updateCoachesSetFilialToNull(id);
        filialRepository.deleteByAddress(address);
    }
}
