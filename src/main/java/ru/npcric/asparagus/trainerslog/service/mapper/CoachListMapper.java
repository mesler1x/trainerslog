package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CoachMapper.class)
public abstract class CoachListMapper {
}
