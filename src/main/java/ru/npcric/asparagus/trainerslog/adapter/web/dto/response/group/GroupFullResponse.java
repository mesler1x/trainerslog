package ru.npcric.asparagus.trainerslog.adapter.web.dto.response.group;

import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.request.StudentDTO;

import java.time.LocalDateTime;
import java.util.List;

public record GroupFullResponse(Long id,
                                String groupName,
                                List<StudentDTO> studentDTOS,
                                List<LocalDateTime> dates,
                                String address) {
}
