package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.TrainingDTO;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, FilialMapper.class})
public abstract class TrainingMapper {
    public abstract TrainingDTO entityToResponse(TrainingEntity trainingEntity);
    public abstract TrainingEntity responseToEntity(TrainingDTO trainingDTO);
    public abstract List<TrainingDTO> entityListToResponseList(List<TrainingEntity> trainingEntityList);
    public abstract List<TrainingEntity> responseListToEntityList(List<TrainingDTO> trainingDTOList);
}
