package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record Group(@NotBlank(message = "Имя группы не может быть пустым") String groupName,
                    @NotNull(message = "Группа не может содержать 0 студентов") List<Student> students,
                    List<LocalDate> dates) { // даты могут быть Null пока их не заполнили
}
