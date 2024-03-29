package ru.npcric.asparagus.trainerslog.adapter.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.GroupNameConstraint;

import java.time.LocalDateTime;
import java.util.List;

public record GroupDTO(@NotNull @NotBlank(message = "Имя группы не может быть пустым")
                    @GroupNameConstraint
                    String groupName,
                       @NotNull(message = "Группа не может содержать 0 студентов") List<StudentDTO> studentDTOS,
                       List<LocalDateTime> dates,
                       String address) { // даты могут быть Null пока их не заполнили
}
