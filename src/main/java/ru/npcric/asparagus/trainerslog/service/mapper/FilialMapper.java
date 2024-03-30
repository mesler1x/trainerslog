package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoachMapper.class, TrainingMapper.class})
public abstract class FilialMapper {
    public abstract FilialEntity reposeToEntity(FilialDTO filialDTO);
    public abstract FilialDTO entityToResponse(FilialEntity filialEntity);
    public abstract List<FilialDTO> entityListToResponseList(List<FilialEntity> filialEntities);
    public abstract List<FilialEntity> responseListToEntityList(List<FilialDTO> filialDTOS);
}
