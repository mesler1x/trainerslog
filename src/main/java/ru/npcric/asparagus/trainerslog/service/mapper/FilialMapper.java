package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.FilialDTO;
import ru.npcric.asparagus.trainerslog.domain.FilialEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoachMapper.class, TrainingMapper.class})
public interface FilialMapper {
    public abstract FilialEntity reposeToEntity(FilialDTO filialDTO);
    public abstract FilialDTO entityToResponse(FilialEntity filialEntity);
}
