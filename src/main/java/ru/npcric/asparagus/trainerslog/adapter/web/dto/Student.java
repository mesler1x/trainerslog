package ru.npcric.asparagus.trainerslog.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Map;

public record Student (@NotNull Ticket ticket,
                       @NotBlank String name,
                       Coach coach,// может быть null пока тренер не назначен
                       Map<LocalDate, Boolean> visitStatistics) {

}
