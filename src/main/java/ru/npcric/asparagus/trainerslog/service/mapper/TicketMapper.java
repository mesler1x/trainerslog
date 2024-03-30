package ru.npcric.asparagus.trainerslog.service.mapper;

import org.mapstruct.Mapper;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.TicketDTO;
import ru.npcric.asparagus.trainerslog.domain.TicketEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public abstract class TicketMapper {
    public abstract TicketDTO entityToResponse(TicketEntity ticketEntity);
    public abstract TicketEntity responseToEntity(TicketDTO ticketDTO);
    public abstract List<TicketDTO> entityListToReponseList(List<TicketEntity> ticketEntities);
    public abstract List<TicketEntity> responseListToEnityList(List<TicketDTO> ticketDTOList);
}
