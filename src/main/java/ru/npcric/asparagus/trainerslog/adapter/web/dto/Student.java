package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.npcric.asparagus.trainerslog.adapter.web.validation.PeopleNameConstraint;

import java.time.LocalDate;
import java.util.Map;

public record Student (@NotNull(message = "Студент не может быть без абонемента") Ticket ticket,
                       @NotBlank(message = "ФИО студента не может быть пустым")
                       @Size(min = 5, max = 40, message = "ФИО студента не может быть меньше 5 или 40 символов")
                       @PeopleNameConstraint
                       String name,
                       Coach coach,// может быть null пока тренер не назначен
                       Map<LocalDate, Boolean> visitStatistics) {

}
