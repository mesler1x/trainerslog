package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record Group(@NotBlank String groupName,
                    @NotNull List<Student> students,
                    List<LocalDate> dates) { // даты могут быть Null пока их не заполнили
}
