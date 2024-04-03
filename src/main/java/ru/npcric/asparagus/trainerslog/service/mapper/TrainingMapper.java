package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.TrainingDTO;
import ru.npcric.asparagus.trainerslog.domain.TrainingEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, FilialMapper.class})
public interface TrainingMapper {
    TrainingDTO entityToResponse(TrainingEntity trainingEntity);
    TrainingEntity responseToEntity(TrainingDTO trainingDTO);
}
