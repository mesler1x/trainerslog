package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.GroupNameConstraint;

import java.time.LocalDateTime;
import java.util.List;

public record Group(@NotBlank(message = "Имя группы не может быть пустым")
                    @GroupNameConstraint
                    String groupName,
                    @NotNull(message = "Группа не может содержать 0 студентов") List<Student> students,
                    List<LocalDateTime> dates,
                    String address) { // даты могут быть Null пока их не заполнили
}
