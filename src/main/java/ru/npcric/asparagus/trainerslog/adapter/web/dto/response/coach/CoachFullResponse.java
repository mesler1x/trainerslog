package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.coach;

import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.filial.FilialDTO;

import java.time.LocalDate;

public record CoachFullResponse (Long id, String name, String email, String phoneNumber, String sex,
                                 LocalDate birthDate, FilialDTO filialDTO, String username) {
}
